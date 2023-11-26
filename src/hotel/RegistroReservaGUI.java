package hotel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RegistroReservaGUI extends JFrame {

    private JTextField nombreField;
    private JTextField apellidoField;

    public RegistroReservaGUI() {
        setTitle("Bienvenido a Hotel Yordana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        pack();
        setSize(400, 250);
        setResizable(false);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(173, 216, 230)); // Color de fondo (Azul claro)

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel apellidoLabel = new JLabel("Apellido:");

        nombreField = new JTextField();
        apellidoField = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInterfazReserva();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana
            }
        });

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(apellidoLabel);
        panel.add(apellidoField);
        panel.add(new JLabel()); // Espaciador
        panel.add(aceptarButton);
        panel.add(new JLabel()); // Espaciador
        panel.add(cancelarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230)); // Color de fondo (Azul claro)
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void abrirInterfazReserva() {
        ReservaGUI reservaGUI = new ReservaGUI(this);
        reservaGUI.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroReservaGUI().setVisible(true);
            }
        });
    }
}

class ReservaGUI extends JFrame {

    private JComboBox<String> tipoHabitacionCombo;
    private JTextField nochesField;
    private JCheckBox wifiCheckBox;
    private JCheckBox gymCheckBox;
    private JCheckBox piscinaCheckBox;
    private JCheckBox transporteCheckBox;
    private JCheckBox limpiezaCheckBox;
    private JCheckBox desayunoCheckBox;

    public ReservaGUI(JFrame parent) {
        setTitle("Reserva de Habitación - Hotel Yordana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        initComponents();

        pack();
        setSize(400, 400);
        setResizable(false);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2, 10, 10));
        panel.setBackground(new Color(255, 240, 245)); // Color de fondo (Lavanda)

        JLabel tipoHabitacionLabel = new JLabel("Tipo de Habitación:");
        JLabel nochesLabel = new JLabel("Noches a Hospedarse:");
        JLabel serviciosAdicionalesLabel = new JLabel("Servicios Adicionales:");

        tipoHabitacionCombo = new JComboBox<>(new String[]{"Doble", "Individual", "Matrimonial", "Suite"});
        nochesField = new JTextField();

        wifiCheckBox = new JCheckBox("Wi-fi ($5/noche)");
        gymCheckBox = new JCheckBox("Gym ($15/noche)");
        piscinaCheckBox = new JCheckBox("Piscina ($18/noche)");
        transporteCheckBox = new JCheckBox("Transporte ($20/noche)");
        limpiezaCheckBox = new JCheckBox("Limpieza ($8/noche)");
        desayunoCheckBox = new JCheckBox("Desayuno ($10/noche)");

        JButton reservarButton = new JButton("Reservar");
        JButton cancelarButton = new JButton("Cancelar");

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInterfazReporte();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(tipoHabitacionLabel);
        panel.add(tipoHabitacionCombo);
        panel.add(nochesLabel);
        panel.add(nochesField);
        panel.add(serviciosAdicionalesLabel);
        panel.add(new JLabel()); // Espaciador
        panel.add(wifiCheckBox);
        panel.add(gymCheckBox);
        panel.add(piscinaCheckBox);
        panel.add(transporteCheckBox);
        panel.add(limpiezaCheckBox);
        panel.add(desayunoCheckBox);
        panel.add(new JLabel()); // Espaciador
        panel.add(reservarButton);
        panel.add(new JLabel()); // Espaciador
        panel.add(cancelarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 245)); // Color de fondo (Lavanda)
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void abrirInterfazReporte() {
        if (validarCampos()) {
            Random random = new Random();
            int numeroHabitacion = random.nextInt(100) + 1; // Número de habitación aleatorio

            String tipoHabitacion = (String) tipoHabitacionCombo.getSelectedItem();
            int cantidadNoches = Integer.parseInt(nochesField.getText());

            double precioHabitacion = calcularPrecioHabitacion(tipoHabitacion, cantidadNoches);
            double precioServicios = calcularPrecioServicios();
            double montoTotal = precioHabitacion + precioServicios;

            ReporteGUI reporteGUI = new ReporteGUI(this, numeroHabitacion, montoTotal, tipoHabitacion, cantidadNoches, precioHabitacion, precioServicios);
            reporteGUI.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        return !nochesField.getText().isEmpty();
    }

    private double calcularPrecioHabitacion(String tipoHabitacion, int cantidadNoches) {
        double precioNoche = 0;

        switch (tipoHabitacion) {
            case "Doble":
                precioNoche = 80;
                break;
            case "Individual":
                precioNoche = 40;
                break;
            case "Matrimonial":
                precioNoche = 150;
                break;
            case "Suite":
                precioNoche = 230;
                break;
        }

        return precioNoche * cantidadNoches;
    }

    private double calcularPrecioServicios() {
        double precioTotal = 0;

        if (wifiCheckBox.isSelected()) precioTotal += 5;
        if (gymCheckBox.isSelected()) precioTotal += 15;
        if (piscinaCheckBox.isSelected()) precioTotal += 18;
        if (transporteCheckBox.isSelected()) precioTotal += 20;
        if (limpiezaCheckBox.isSelected()) precioTotal += 8;
        if (desayunoCheckBox.isSelected()) precioTotal += 10;

        return precioTotal;
    }
}

class ReporteGUI extends JFrame {

    public ReporteGUI(JFrame parent, int numeroHabitacion, double montoTotal, String tipoHabitacion, int cantidadNoches, double precioHabitacion, double precioServicios) {
        setTitle("Reporte de Venta - Hotel Yordana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        initComponents(numeroHabitacion, montoTotal, tipoHabitacion, cantidadNoches, precioHabitacion, precioServicios);

        pack();
        setSize(500, 350);
        setResizable(false);
    }

    private void initComponents(int numeroHabitacion, double montoTotal, String tipoHabitacion, int cantidadNoches, double precioHabitacion, double precioServicios) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBackground(new Color(240, 255, 240)); // Color de fondo (Menta)

        // Panel superior con el logo y título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 255, 240));
        JLabel titleLabel = new JLabel("Hotel Yordana");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);

        JLabel numeroHabitacionLabel = new JLabel("Número de Habitación: " + numeroHabitacion);
        JLabel tipoHabitacionLabel = new JLabel("Tipo de Habitación: " + tipoHabitacion);
        JLabel cantidadNochesLabel = new JLabel("Noches a Hospedarse: " + cantidadNoches);
        JLabel precioHabitacionLabel = new JLabel("Precio de Habitación (" + tipoHabitacion + "): $" + precioHabitacion);
        JLabel precioServiciosLabel = new JLabel("Precio de Servicios: $" + precioServicios);
        JLabel montoTotalLabel = new JLabel("Monto Total a Pagar: $" + montoTotal);

        JButton pagarButton = new JButton("Pagar");
        JButton cancelarButton = new JButton("Cancelar");

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡Pago realizado con éxito!");
                dispose();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(headerPanel);
        panel.add(numeroHabitacionLabel);
        panel.add(tipoHabitacionLabel);
        panel.add(cantidadNochesLabel);
        panel.add(precioHabitacionLabel);
        panel.add(precioServiciosLabel);
        panel.add(montoTotalLabel);
        panel.add(new JLabel()); // Espaciador
        panel.add(pagarButton);
        panel.add(cancelarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 255, 240)); // Color de fondo (Menta)
        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
