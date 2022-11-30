import com.github.javafaker.Faker;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    private static Statement stmt;
    private static Connection con;


    public static void main(String[] args) {

        Faker faker = new Faker(new Locale("es"));


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/club","root","root");
            stmt=con.createStatement();



            //Limpiamos la base de datos
            deleteTable("pertenece");
            deleteTable("cronograma");
            deleteTable("puede_desarrollar");
            deleteTable("puede_dar");
            deleteTable("area");
            deleteTable("profesional");
            deleteTable("actividad_arancelada");
            deleteTable("actividad");
            deleteTable("grupo_familiar");
            deleteTable("socio_titular");
            deleteTable("socio");
            deleteTable("categoria");
            //Falta indicar orden inverso al de creacion


//            deleteTable("cuota_social");
//            deleteTable("pago");




//            deleteTable("se_inscribe");
//            deleteTable("socio");


//            //Creamos las categorias
            cargarCategoria("infantiles", 1000);
            cargarCategoria("mayores", 2000);
            cargarCategoria("vitalicios", 3000);



            List<Integer> categoriaId = obtenerIds("select * from categoria");
            //Creamos socios
            for (int i = 0; i <100; i++)
                cargarSocio(null, faker.number().numberBetween(categoriaId.get(0),categoriaId.get(categoriaId.size() - 1)), faker.number().numberBetween(0,10),null, faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), new Date(faker.date().birthday().getTime()), faker.bool().bool());


            //Creamos actividad
            cargarActividad("Natacion", faker.bool().bool());
            cargarActividad("Futbol", faker.bool().bool());
            cargarActividad("Ping Pong", faker.bool().bool());
            cargarActividad("Hockey", faker.bool().bool());
            cargarActividad("Rugby", faker.bool().bool());
            cargarActividad("Volley", faker.bool().bool());
            cargarActividad("Handball", faker.bool().bool());




            //Creamos actividad arancelada
            List<Integer> actividadId = obtenerIds("select * from actividad a where a.modalidad = true");
            List<String> tipos_cuota = new ArrayList<>();
            tipos_cuota.add("Bimestral");
            tipos_cuota.add("Semestral");
            tipos_cuota.add("Cuatrimestral");
            tipos_cuota.add("Mensual");
            tipos_cuota.add("Anual");
            actividadId.stream().forEach(idActividad -> {
                cargarActividadArancelada(idActividad, (float)faker.number().randomDouble(0,1000,3000), tipos_cuota.get(faker.number().numberBetween(0,tipos_cuota.size()-1)));
            });


            //Creamos profesional
            for (int i = 0; i <40; i++)
                cargarProfesional(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress(), new Date(faker.date().birthday().getTime()));


            //Creamos area
            List<String> estados_mantenimiento = new ArrayList<>();
            estados_mantenimiento.add("Construccion");
            estados_mantenimiento.add("Disponible");
            estados_mantenimiento.add("Reparacion");
            for (int i = 0; i <10; i++)
                cargarArea(faker.address().streetAddress(), faker.number().numberBetween(10,100), estados_mantenimiento.get(faker.number().numberBetween(0,estados_mantenimiento.size()-1)));



            //Creamos puede_dar
            List<Integer> ids_profesional = obtenerIds("select * from profesional");
            List<Integer> ids_actividad = obtenerIds("select * from actividad");
            for (int i = 0; i < ids_profesional.size(); i++)
                cargarPuedeDar(ids_profesional.get(faker.number().numberBetween(0, ids_profesional.size()-1)), ids_actividad.get(faker.number().numberBetween(0, ids_actividad.size()-1)));



            //Creamos puede_desarrollar
            List<Integer> ids_area = obtenerIds("select * from area");
            for (int i = 0; i < ids_area.size(); i++)
                cargarPuedeDesarrollar(ids_area.get(faker.number().numberBetween(0, ids_area.size()-1)), ids_actividad.get(faker.number().numberBetween(0, ids_actividad.size()-1)) );


            //Creamos cronograma
            List<String> diasName = new ArrayList<>();
            diasName.add("Lunes");
            diasName.add("Martes");
            diasName.add("Miercoles");
            diasName.add("Jueves");
            diasName.add("Viernes");
            diasName.add("Sabado");
            diasName.add("Domingo");



            for (int i = 0; i < ids_profesional.size(); i++)
                cargarCronograma(ids_actividad.get(faker.number().numberBetween(0, ids_actividad.size()-1)), ids_area.get(faker.number().numberBetween(0, ids_area.size()-1)), ids_profesional.get(faker.number().numberBetween(0, ids_profesional.size()-1)), diasName.get(faker.number().numberBetween(0, diasName.size()-1)), new Time(faker.date().birthday().getTime()), new Time(faker.date().birthday().getTime()), new Date((new SimpleDateFormat("yyyy-MM-dd")).parse("2022-10-10").getTime()));


            //Creamos pertenece





//            ResultSet rs=stmt.executeQuery("select * from categoria");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+);

//            String query = "INSERT INTO `club`.`pake2` (`id_pake`,`edad`) VALUES (?,?);";
//            for (int i=2; i<10; i++){
//                PreparedStatement preparedStmt = con.prepareStatement(query);
//                preparedStmt.setInt (1, i);
//                preparedStmt.setInt (2, i);
//                preparedStmt.execute();
//            }
            con.close();
        }catch(Exception e){ System.out.println(e);}

    }

//    public static void cargar

   public static void cargarCronograma(int idActividad, int idArea, int idProfesional, String dia, Time horaInicio, Time horaFin, Date periodo){
       String query = "INSERT INTO `club`.`cronograma` VALUES (?,?,?,?,?,?,YEAR(?));";
       try {
           PreparedStatement preparedStmt = con.prepareStatement(query);
           preparedStmt.setInt (1, idActividad);
           preparedStmt.setInt (2, idArea);
           preparedStmt.setInt (3, idProfesional);
           preparedStmt.setString(4, dia);
           preparedStmt.setTime(5,horaInicio);
           preparedStmt.setTime(6,horaFin);
           preparedStmt.setObject(7,periodo);
           preparedStmt.execute();
       } catch (SQLException e) {

       }
   }

    public static void cargarPuedeDesarrollar(int idArea, int idActividad){

        String query = "INSERT INTO `club`.`puede_desarrollar` VALUES (?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, idArea);
            preparedStmt.setInt (2, idActividad);
            preparedStmt.execute();
        } catch (SQLException e) {

        }

    }
    public static void cargarPuedeDar(int idProfesional, int idActividad){
        String query = "INSERT INTO `club`.`puede_dar` VALUES (?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, idProfesional);
            preparedStmt.setInt (2, idActividad);
            preparedStmt.execute();
        } catch (SQLException e) {

        }
    }

    public static void cargarArea(String ubicacion, int capacidad, String estado_mantenimiento){

        String query = "INSERT INTO `club`.`area` VALUES (?,?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setString(2, ubicacion);
            preparedStmt.setInt(3, capacidad);
            preparedStmt.setString(4, estado_mantenimiento);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void cargarProfesional(String nombre, String apellido, String telefono, String email, Date fecha_nacimiento){

        String query = "INSERT INTO `club`.`profesional` VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setString(2, nombre);
            preparedStmt.setString(3, apellido);
            preparedStmt.setString(4, telefono);
            preparedStmt.setString(5, email);
            preparedStmt.setDate(6,fecha_nacimiento);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarActividadArancelada(int id,float arancel, String tipo_cuota){

        String query = "INSERT INTO `club`.`actividad_arancelada` VALUES (?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, id);
            preparedStmt.setFloat(2, arancel);
            preparedStmt.setString(3, tipo_cuota);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void cargarActividad(String nombre, boolean modalidad){

        String query = "INSERT INTO `club`.`actividad` VALUES (?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setString(2, nombre);
            preparedStmt.setBoolean(3, modalidad);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void cargarCategoria(String nombre, float monto_base){

        String query = "INSERT INTO `club`.`categoria` VALUES (?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setString(2, nombre);
            preparedStmt.setFloat(3, monto_base);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void cargarSocio(Integer nro_base, int id_categoria, int nro_orden, String num_celular, String nombre, String apellido, String email, Date fecha_nacimiento, boolean titularidad){
        String query = "INSERT INTO `club`.`socio` VALUES (?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setObject(2, nro_base);
            preparedStmt.setInt (3, nro_orden);
            preparedStmt.setInt (4, id_categoria);
            preparedStmt.setObject(5, num_celular);
            preparedStmt.setString (6, nombre);
            preparedStmt.setString (7, apellido);
            preparedStmt.setString (8, email);
            preparedStmt.setDate(9, fecha_nacimiento);
            preparedStmt.setBoolean(10, titularidad);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable(String tableName){

        String query = "delete from " +  tableName;
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();

            query = "ALTER TABLE "+ tableName + " AUTO_INCREMENT = 1";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<Integer> obtenerIds(String query){

        List<Integer> ids = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
                ids.add(rs.getInt(1));
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
