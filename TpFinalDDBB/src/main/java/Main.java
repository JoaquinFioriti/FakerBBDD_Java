import com.github.javafaker.Faker;

import java.sql.*;
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
            deleteTable("profesional");
            deleteTable("actividad_arancelada");
            deleteTable("actividad");
            deleteTable("grupo_familiar");
            deleteTable("socio_titular");
            deleteTable("socio");
            deleteTable("categoria");
            //Falta indicar orden inverso al de creacion

//            deleteTable("area");
//            deleteTable("cronograma");
//            deleteTable("cuota_social");
//            deleteTable("pago");
//            deleteTable("pertenece");

//            deleteTable("puede_dar");
//            deleteTable("puede_desarrollar");
//            deleteTable("se_inscribe");
//            deleteTable("socio");


//            //Creamos las categorias
            cargarCategoria("infantiles", 1000);
            cargarCategoria("mayores", 2000);
            cargarCategoria("vitalicios", 3000);



            List<Integer> categoriaId = obtenerIds("select * from categoria");
            //Creamos socios
            for (int i = 0; i <100; i++)
                cargarSocio(null, faker.number().numberBetween(categoriaId.get(0),categoriaId.get(categoriaId.size() - 1)), null, faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), new Date(faker.date().birthday().getTime()), faker.bool().bool());


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

    public static void cargarSocio(Integer nro_base, int id_categoria, String num_celular, String nombre, String apellido, String email, Date fecha_nacimiento, boolean titularidad){
        String query = "INSERT INTO `club`.`socio` VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 0);
            preparedStmt.setObject(2, nro_base);
            preparedStmt.setInt (3, id_categoria);
            preparedStmt.setObject(4, num_celular);
            preparedStmt.setString (5, nombre);
            preparedStmt.setString (6, apellido);
            preparedStmt.setString (7, email);
            preparedStmt.setDate(8, fecha_nacimiento);
            preparedStmt.setBoolean(9, titularidad);
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
