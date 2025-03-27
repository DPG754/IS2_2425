package es.unican.is2.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;


public class VistaFuncionario extends JFrame {

    private JPanel contentPane;
    private JTextField txtDniContribuyente;
    private JTextField txtTotalContribuyente;
    private JTextField txtNombreContribuyente;
    private JList<String> listMatriculasVehiculos;
    private DefaultListModel<String> listModel;
    private JButton btnBuscar;
    private IInfoImpuestoCirculacion info;

    public VistaFuncionario(IInfoImpuestoCirculacion info) {
        this.info = info;
        init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 441, 341);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        listModel = new DefaultListModel<>();

        txtTotalContribuyente = new JTextField();
        txtTotalContribuyente.setBounds(230, 251, 86, 20);
        contentPane.add(txtTotalContribuyente);
        txtTotalContribuyente.setColumns(10);
        txtTotalContribuyente.setName("txtTotalContribuyente");

        JLabel lblTotalContribuyente = new JLabel("Total A Pagar");
        lblTotalContribuyente.setBounds(137, 254, 99, 14);
        contentPane.add(lblTotalContribuyente);

        listMatriculasVehiculos = new JList<>();
        listMatriculasVehiculos.setBounds(230, 98, 121, 116);
        contentPane.add(listMatriculasVehiculos);
        listMatriculasVehiculos.setBorder(new LineBorder(new Color(0, 0, 0)));
        listMatriculasVehiculos.setModel(listModel);
        listMatriculasVehiculos.setName("listMatriculasVehiculos");

        JLabel lblVehiculos = new JLabel("Vehiculos");
        lblVehiculos.setBounds(149, 93, 65, 14);
        contentPane.add(lblVehiculos);

        JLabel lblNombreContribuyente = new JLabel("Nombre");
        lblNombreContribuyente.setBounds(155, 54, 65, 14);
        contentPane.add(lblNombreContribuyente);

        txtNombreContribuyente = new JTextField();
        txtNombreContribuyente.setBounds(230, 51, 121, 20);
        contentPane.add(txtNombreContribuyente);
        txtNombreContribuyente.setColumns(10);
        txtNombreContribuyente.setName("txtNombreContribuyente");

        JLabel lblDatosContribuyente = new JLabel("Datos Contribuyente");
        lblDatosContribuyente.setBounds(230, 11, 149, 14);
        contentPane.add(lblDatosContribuyente);

        txtDniContribuyente = new JTextField();
        txtDniContribuyente.setBounds(10, 51, 113, 20);
        contentPane.add(txtDniContribuyente);
        txtDniContribuyente.setColumns(10);
        txtDniContribuyente.setName("txtDniContribuyente");

        JLabel lblDniContribuyente = new JLabel("DNI Contribuyente");
        lblDniContribuyente.setBounds(21, 27, 139, 14);
        contentPane.add(lblDniContribuyente);
        lblDniContribuyente.setName("lblDniContribuyente");

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(21, 122, 89, 23);
        contentPane.add(btnBuscar);
        btnBuscar.setName("btnBuscar");

        btnBuscar.addActionListener(e -> rellenaDatosContribuyente(txtDniContribuyente.getText()));
    }

    private void rellenaDatosContribuyente(String dni) {
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            Contribuyente c = info.contribuyente(dni.trim()); // Eliminar espacios extra
            if (c != null) {
                SwingUtilities.invokeLater(() -> {
                    txtNombreContribuyente.setText(c.getNombre() + " " + c.getApellido2() + " " + c.getApellido1());
                    txtTotalContribuyente.setText(df.format(c.totalImpuestoCirculacion()));
                    listModel.clear();
                    List<Vehiculo> vehiculos = c.getVehiculos();
                    for (Vehiculo v : vehiculos) {
                        listModel.addElement(v.getMatricula());
                    }
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    txtNombreContribuyente.setText("DNI Incorrecto");
                    txtTotalContribuyente.setText("0");
                    listModel.clear();
                });
            }
        } catch (DataAccessException e) {
            SwingUtilities.invokeLater(() -> txtNombreContribuyente.setText("Error BBDD"));
            e.printStackTrace();
        }
    }
}