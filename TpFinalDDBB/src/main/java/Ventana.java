import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JScrollPane;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.util.List;

public class Ventana extends JFrame {

    private JPanel contentPane;
    private JPanel panelContenedor;
    private JPanel panelOeste;
    private JLabel lblListaAsociados;
    private JScrollPane scrollPane;
    private JList<Socio> listAsociados;
    private DefaultListModel<Socio> modeloLista= new DefaultListModel<Socio>();
    private DefaultListModel<Actividad> modeloListaAct= new DefaultListModel<Actividad>();
    private JButton btnVerSocios;
    private JPanel panelOeste_1;
    private JLabel lblListaAsociados_1;
    private JList<Actividad> listActividades;
    private JScrollPane scrollPane_1;

    private JButton btnSocioActividad;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana frame = new Ventana();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Ventana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 533, 352);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelContenedor = new JPanel();
        contentPane.add(panelContenedor);
        panelContenedor.setLayout(null);

        panelOeste = new JPanel();
        panelOeste.setBounds(0, 0, 169, 303);
        panelContenedor.add(panelOeste);
        panelOeste.setLayout(null);

        lblListaAsociados = new JLabel("                  Lista de Asociados");
        lblListaAsociados.setBounds(0, 0, 169, 14);
        panelOeste.add(lblListaAsociados);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 14, 169, 289);
        panelOeste.add(scrollPane);

        listAsociados = new JList<Socio>();
        listAsociados.setAlignmentX(Component.RIGHT_ALIGNMENT);
        listAsociados.setModel(modeloLista);
        scrollPane.setViewportView(listAsociados);

        btnVerSocios = new JButton("Ver socios");
        btnVerSocios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO:HACER
                List<Socio> socios = Main.findAllSocios("select * from socio");
                modeloLista.clear();
                modeloLista.addAll(socios);
            }
        });
        btnVerSocios.setBounds(183, 29, 135, 23);
        panelContenedor.add(btnVerSocios);

        panelOeste_1 = new JPanel();
        panelOeste_1.setLayout(null);
        panelOeste_1.setBounds(328, 0, 169, 303);
        panelContenedor.add(panelOeste_1);

        lblListaAsociados_1 = new JLabel("                  Lista de Actividades");
        lblListaAsociados_1.setBounds(-10, 0, 169, 14);
        panelOeste_1.add(lblListaAsociados_1);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 16, 167, 287);
        panelOeste_1.add(scrollPane_1);

        listActividades = new JList<Actividad>();
        listActividades.setModel(modeloListaAct);
        scrollPane_1.setViewportView(listActividades);
        listActividades.setAlignmentX(1.0f);


        btnSocioActividad = new JButton("Socio-Actividad");
        btnSocioActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Socio socio = modeloLista.get(listAsociados.getSelectedIndex());
                List<Actividad> actividades = Main.findAllActividades("select a.id_actividad, a.nombre from actividad a, se_inscribe si where si.id_socio = " + socio.getIdSocio());
                modeloListaAct.clear();
                modeloListaAct.addAll(actividades);
            }
        });
        btnSocioActividad.setBounds(183, 89, 135, 23);
        panelContenedor.add(btnSocioActividad);



        this.setVisible(true);
    }
}



