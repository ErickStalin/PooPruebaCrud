import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class form1 {
    PreparedStatement ps;
    private JTextField color;
    private JTextField tipo;
    private JTextField placa;
    private JTextField modelo;
    private JButton GUARDARButton;
    private JPanel p1;
    private JButton ACTUALIZARButton;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JComboBox comboBox1;
    PreparedStatement pd;

    public static Connection getConecction() {
        Connection cn = null;
        String base = "vehiculo"; //Sombre de la BD
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y tipo BD
        String user = "root"; //Usuario
        String pass = "0986167219"; //Contraseña
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return cn;
    }
    public form1() {
        GUARDARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                try {
                    cn = getConecction();
                    ps = cn.prepareStatement("INSERT INTO datosVehiculo (placa, tipo , color ,modelo) values (?,?,?,?)");
                    ps.setString(1, placa.getText());
                    ps.setString(2, tipo.getText());
                    ps.setString(3, color.getText());
                    ps.setString(4, modelo.getText());
                    System.out.println(ps);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Coche guardado con exito");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                try{
                cx = getConecction();
                String qr = "select * from datosVehiculo  where placa = "+ placa.getText()+";";
                Statement s = cx.createStatement();
                ResultSet rs = s.executeQuery(qr);
                System.out.println(rs);
                while(rs.next()) {
                    tipo.setText(rs.getString(2));
                    color.setText(rs.getString(3));
                    modelo.setText(rs.getString(4));
                }
                cx.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            }
        });
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection ct;
                try {
                    String qr = "Update datosVehiculo set tipo = ?, color = ?, modelo = ? where placa = "+ placa.getText();
                    ct = getConecction();
                    pd = ct.prepareStatement(qr);
                    pd.setString(1, tipo.getText());
                    pd.setString(2, color.getText());
                    pd.setString(3, modelo.getText());
                    pd.executeUpdate();
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Coche actualizado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    ct.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                String id = placa.getText();
                try {
                    con = getConecction();
                    pd = con.prepareStatement("DELETE FROM datosVehiculo WHERE placa="+id);
                    pd.executeUpdate();
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Error");
                    } else {
                        JOptionPane.showMessageDialog(null,"Coche eliminado con exito");
                    }
                    con.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("form1");
        fr.setContentPane(new form1().p1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
    }

}
