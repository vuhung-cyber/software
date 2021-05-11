import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javax.rmi.CORBA.Tie;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.sql.*;

public class ControllerMain implements Initializable {
    //tap one
    @FXML
    Button list;
    @FXML
    Button add;
    @FXML
    Button fix;
    @FXML
    Button delete;
    @FXML
    Button done;
    @FXML
    Button doneDelete;
    @FXML
    Button doneFix;
    @FXML
    ListView<String> showList;
    @FXML
    TextField CustomerNumber;
    @FXML
    TextField CustomerName;
    @FXML
    TextField IdCardName;
    @FXML
    TextField Gender;
    @FXML
    TextField Phone;
    @FXML
    TextField Address;
    @FXML
    TextField Birthday;
    Date birthday;
    @FXML
    TextField StartDay;
    Date startday;
    @FXML
    TextField Type;
    @FXML
    TextField deleteFlowName;
    @FXML
    TextField deleteFlowNumber;
    @FXML
    TextField CustomerNumberOld, CustomerNumberNew;
    @FXML
    TextField CustomerNameOld, CustomerNameNew;
    @FXML
    TextField IdCardNameOld, IdCardNameNew;
    @FXML
    TextField GenderOld, GenderNew;
    @FXML
    TextField PhoneOld, PhoneNew;
    @FXML
    TextField AddressOld, AddressNew;
    @FXML
    TextField BirthdayOld, BirthdayNew;
    Date birthdayOld, birthdayNew;
    @FXML
    TextField StartDayOld, StartDayNew;
    Date startdayOld, startdayNew;
    @FXML
    TextField TypeOld, TypeNew;

    //tap two
    @FXML
    Button addNewNumberElectric;
    @FXML
    Button invoice;
    @FXML
    Button receipt;
    @FXML
    TextField maSoHoaDon;
    @FXML
    TextField soDienThoai;
    @FXML
    TextField maSoDanCu;
    @FXML
    TextField tenHoDanCu;
    @FXML
    TextField diaChi;
    @FXML
    TextField soCMND;
    @FXML
    TextField ngayLapHoaDon;
    @FXML
    TextField loaiDien;
    @FXML
    TextField soCongToCu;
    @FXML
    TextField soCongToMoi;
    @FXML
    TextField soTienPhaiDong;
    @FXML
    ListView showOneName;

    //tap Three
    @FXML
    Button search;
    @FXML
    TextField one;
    @FXML
    TextField two;
    @FXML
    TextArea showInf;

    //Tap Four
    @FXML
    Button baoCaoSoDien;
    @FXML
    Button baoCaoSoTien;
    @FXML
    TextArea textSoDien;
    @FXML
    TextArea textSoTien;

    @FXML
    LineChart lineChartSoDien;
    @FXML
    CategoryAxis xSoDien;
    @FXML
    NumberAxis ySoDien;
    XYChart.Series seriesDien = new XYChart.Series();


    @FXML
    LineChart lineChartSoTien;
    @FXML
    CategoryAxis xSoTien;
    @FXML
    NumberAxis ySoTien;
    XYChart.Series seriesTien = new XYChart.Series();

    /*Funtion Tap One*/

    //add ListView
    public void addList() {
        showList.setVisible(true);
        ObservableList list = FXCollections.observableArrayList();
        while (true) {
            try {
                if (!Server.rs.next()) break;
                else
                    list.add(Server.rs.getString(2) + "\t\t" + Server.rs.getInt(5));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        showList.getItems().addAll(list);
        System.out.println("hi");
    }
    //show List
    public void presslist(ActionEvent actionEvent) {
        addList();
        setVisibleOfAdd(false);
        done.setVisible(false);
        setVisibleOfDeteleView(false);
        doneFix.setVisible(false);
        setFixText(false);
    }
    //set delete view
    public void setVisibleOfDeteleView(Boolean check) {
        deleteFlowName.setVisible(check);
        deleteFlowNumber.setVisible(check);
        doneDelete.setVisible(check);
    }
    //set Add view
    public void setVisibleOfAdd(Boolean check){
        CustomerNumber.setVisible(check);
        CustomerName.setVisible(check);
        IdCardName.setVisible(check);
        Gender.setVisible(check);
        Phone.setVisible(check);
        Address.setVisible(check);
        Birthday.setVisible(check);
        StartDay.setVisible(check);
        Type.setVisible(check);
    }
    //set Fix Text view
    public void setFixText(boolean check) {
        CustomerNumberOld.setVisible(check);
        CustomerNumberNew.setVisible(check);
        CustomerNameOld.setVisible(check);
        CustomerNameNew.setVisible(check);
        IdCardNameOld.setVisible(check);
        IdCardNameNew.setVisible(check);
        GenderOld.setVisible(check);
        GenderNew.setVisible(check);
        PhoneOld.setVisible(check);
        PhoneNew.setVisible(check);
        AddressOld.setVisible(check);
        AddressNew.setVisible(check);
        BirthdayOld.setVisible(check);
        BirthdayNew.setVisible(check);
        StartDayNew.setVisible(check);
        StartDayOld.setVisible(check);
        TypeNew.setVisible(check);
        TypeOld.setVisible(check);
    }
    //press Add
    public void setAdd() {
        showList.setVisible(false);
        setVisibleOfAdd(true);
        done.setVisible(true);
        setVisibleOfDeteleView(false);
        doneFix.setVisible(false);
        setFixText(false);
    }
    //Press Done
    public void pressDone() throws SQLException {
        String s1 = CustomerNumber.getText();
        String s2 = CustomerName.getText();
        String s3 = IdCardName.getText();
        String s4 = Gender.getText();
        String s5 = Phone.getText();
        String s6 = Address.getText();
        String s7 = Birthday.getText();
        String s8 = StartDay.getText();
        String s9 = Type.getText();

        int customerNumber = Integer.parseInt(s1);
        int phone = Integer.parseInt(s5);
        birthday = Date.valueOf(s7);
        startday = Date.valueOf(s8);
        if(Server.addCustomer(customerNumber, s2, s3, s4, phone, s6, birthday, startday, s9)) {
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Congratulations");
            al.setContentText("Đã thêm thành công");
            al.show();
        }else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setContentText("Lỗi!");
            al.show();
        }
        clearTextField();
    }
    //press sua
    public void setFix(){
        showList.setVisible(false);
        setVisibleOfAdd(false);
        done.setVisible(false);
        doneFix.setVisible(true);
        setVisibleOfDeteleView(false);
        setFixText(true);
    }
    //press Done fix
    public void pressDoneFix() {
        boolean checking = false;
        String nameOld = "";
        if(!CustomerNameOld.getText().isEmpty()) {
            nameOld = CustomerNameOld.getText();
        }
        if(!CustomerNameNew.getText().isEmpty() && !CustomerNameOld.getText().isEmpty()) {
            Server.fixToString(CustomerNameOld.getText(), CustomerNameNew.getText());
            checking = true;
        }

        if(!IdCardNameNew.getText().isEmpty() && !IdCardNameOld.getText().isEmpty()) {
            Server.fixToString(IdCardNameOld.getText(), IdCardNameNew.getText());
            checking = true;
        }
        if(!GenderNew.getText().isEmpty() && !GenderOld.getText().isEmpty()) {
            Server.fixToString(GenderOld.getText(), GenderNew.getText());
            checking = true;
        }
        if(!PhoneNew.getText().isEmpty() && !PhoneOld.getText().isEmpty()) {
            Server.fixToInt(nameOld, Integer.parseInt(PhoneNew.getText()));
            checking = true;
        }
        if(!AddressNew.getText().isEmpty() && !AddressOld.getText().isEmpty()) {
            Server.fixToString(AddressOld.getText(), AddressNew.getText());
            checking = true;
        }
        if(!TypeOld.getText().isEmpty() && !TypeNew.getText().isEmpty()) {
            Server.fixToString(TypeOld.getText(), TypeNew.getText());
            checking = true;
        }
        if(!BirthdayNew.getText().isEmpty() && !BirthdayOld.getText().isEmpty()) {
            birthday = Date.valueOf(BirthdayOld.getText());
            startday = Date.valueOf(BirthdayNew.getText());
            Server.fixToDate(birthday, startday);
            checking = true;
        }
        if(!StartDayOld.getText().isEmpty() && !StartDayNew.getText().isEmpty()) {
            birthday = Date.valueOf(StartDayOld.getText());
            startday = Date.valueOf(StartDayNew.getText());
            Server.fixToDate(birthday, startday);
            checking = true;
        }
        if(checking) {
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Congratulations");
            al.setContentText("Đã thêm thành công");
            al.show();
        }
        clearFix();
    }
    //press Delete
    public void setDelete(){
        showList.setVisible(false);
        setVisibleOfAdd(false);
        done.setVisible(false);
        setVisibleOfDeteleView(true);
        doneFix.setVisible(false);
        setFixText(false);
    };
    //press Delete button
    public void pressDelete() throws SQLException {
        if(!deleteFlowName.getText().isEmpty()) {
            String s = deleteFlowName.getText();
            if(!Server.delete(s)) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Congratulations");
                al.setContentText("Đã thêm thành công");
                al.show();
            }else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setContentText("Lỗi!");
                al.show();
            }
        }
        if(!deleteFlowNumber.getText().isEmpty()) {
            int s = Integer.parseInt(deleteFlowNumber.getText());
            if(!Server.delete(s)) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Congratulations");
                al.setContentText("Đã thêm thành công");
                al.show();
            }else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setContentText("Lỗi!");
                al.show();
            }
        }
        clearTextDelete();
    };
    //clear Textfield ADD
    public void clearTextField(){
        CustomerNumber.clear();
        CustomerName.clear();
        IdCardName.clear();
        Gender.clear();
        Phone.clear();
        Address.clear();
        Birthday.clear();
        StartDay.clear();
        Type.clear();
    }
    //Clear Textfield Delete
    public void clearTextDelete(){
        deleteFlowNumber.clear();
        deleteFlowName.clear();
    }
    //Clear TextField Fix
    public void clearFix() {
        CustomerNameOld.clear();
        CustomerNameNew.clear();
        CustomerNameOld.clear();
        CustomerNameNew.clear();
        IdCardNameOld.clear();
        IdCardNameNew.clear();
        GenderOld.clear();
        GenderNew.clear();
        PhoneOld.clear();
        PhoneNew.clear();
        AddressOld.clear();
        AddressNew.clear();
        BirthdayOld.clear();
        BirthdayNew.clear();
        StartDayNew.clear();
        StartDayOld.clear();
        TypeNew.clear();
        TypeOld.clear();
    }

    /*Funtion Tap Two*/

    //set Text tap 2
    public void setFieldTapTwo(){
        maSoHoaDon.clear();
        soDienThoai.clear();
        maSoDanCu.clear();
        loaiDien.clear();
        tenHoDanCu.clear();
        soCongToCu.clear();
        soCongToMoi.clear();
        diaChi.clear();
        soCMND.clear();
        soTienPhaiDong.clear();
        ngayLapHoaDon.clear();
    }
    //searching ten ho dan cu.
    @FXML
    public void searchingName() {
        tenHoDanCu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue.trim().isEmpty())) {
                showOneName.setVisible(true);
                try {
                    Server.rs = Server.stmt.executeQuery(
                            "SELECT * FROM CUSTOMERS WHERE customerName LIKE '" + newValue + "%" + "'");
                    ObservableList<String> tmp = FXCollections.observableArrayList();
                    while (Server.rs.next()) {
                        tmp.add(Server.rs.getString(2));
                        break;
                    }
                    showOneName.setItems(tmp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                showOneName.setVisible(false);
            }
        });
    }
    //get select list view
    @FXML
    public void getSelection() {
        String s = (String) showOneName.getSelectionModel().getSelectedItem();
        try {
            Server.rs = Server.stmt.executeQuery(
                    "SELECT * FROM CUSTOMERS WHERE customerName = '" + s + "'");

            while (Server.rs.next()) {
                tenHoDanCu.setText(Server.rs.getString(2));
                maSoDanCu.setText(String.valueOf(Server.rs.getInt(1)));
                diaChi.setText(Server.rs.getString(6));
                soCMND.setText(Server.rs.getString(3));
                soDienThoai.setText(String.valueOf(Server.rs.getInt(5)));
                loaiDien.setText(Server.rs.getString(9));

                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        showOneName.setVisible(false);
    }
    //press lap hoa don
    public void setLapHoaDon() {
        if(!(soCongToCu.getText().isEmpty() && soCongToMoi.getText().isEmpty())) {
            int congTo1 = Integer.parseInt(soCongToCu.getText());
            int congTo2 = Integer.parseInt(soCongToMoi.getText());
            int soDien = congTo2-congTo1;
            int Tien = 0;
            if(soDien <= 50) {
                Tien = soDien * 1500;
            }else if(50 < soDien && soDien <= 100) {
                Tien = 50 * 1500 + (soDien - 50) * 1700;
            }else  if(soDien > 100 && soDien <= 200) {
                Tien = 50 * 1500 + (50) * 1700 + (soDien - 100) * 2000;
            }else {
                Tien = 50 * 1500 + (50) * 1700 + (100) * 2000 + (soDien - 200) * 2500;
            }
            String tien = String.valueOf(Tien) + " dong";
            soTienPhaiDong.setText(tien);
            //System.out.println((congTo2 - congTo1) * 2000);
        }
        String s = String.valueOf(java.time.LocalDateTime.now());
        ngayLapHoaDon.setText(s);
    }
    //tinh tien
    public int tinhTien(int soDien) {
        int Tien = 0;
        if(soDien <= 50) {
            Tien = soDien * 1500;
        }else if(50 < soDien && soDien <= 100) {
            Tien = 50 * 1500 + (soDien - 50) * 1700;
        }else  if(soDien > 100 && soDien <= 200) {
            Tien = 50 * 1500 + (50) * 1700 + (soDien - 100) * 2000;
        }else {
            Tien = 50 * 1500 + (50) * 1700 + (100) * 2000 + (soDien - 200) * 2500;
        }
        return Tien;
    }
    public double tinhTien(double soDien) {
        double Tien = 0;
        if(soDien <= 50) {
            Tien = soDien * 1500;
        }else if(50 < soDien && soDien <= 100) {
            Tien = 50 * 1500 + (soDien - 50) * 1700;
        }else  if(soDien > 100 && soDien <= 200) {
            Tien = 50 * 1500 + (50) * 1700 + (soDien - 100) * 2000;
        }else {
            Tien = 50 * 1500 + (50) * 1700 + (100) * 2000 + (soDien - 200) * 2500;
        }
        return Tien;
    }
    //press xuat hoa don
    //Lỗi chưa fix
    public void setXuatHoaDon() {
        String sql = "insert into bills(billNumber, customerNumber, month, amount, type, pay)" +
                " value(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = Server.connect.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(maSoHoaDon.getText()));
            ps.setInt(2, Integer.parseInt(maSoDanCu.getText()));
            ps.setInt(4, Integer.parseInt(soCongToMoi.getText()) - Integer.parseInt(soCongToCu.getText()));
            ps.setString(5, loaiDien.getText());
            ps.setString(6, soTienPhaiDong.getText());
            String s = ngayLapHoaDon.getText();
            String newS = s.substring(5, 7);
            System.out.println(newS);
            ps.setString(3, newS);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //add số điện
    public void setAddNewNumberElectric() {
        if(!(maSoDanCu.getText().isEmpty() || soCongToMoi.getText().isEmpty()
                || soCongToCu.getText().isEmpty() || ngayLapHoaDon.getText().isEmpty())) {
            String s = maSoDanCu.getText();
            String s1 = null;
            int s2 = -1;
            int s3 = -1;
            try {
                Server.rs = Server.stmt.executeQuery(
                        "SELECT * FROM consume WHERE customerNumber = '" + s + "'");

                while (Server.rs.next()) {
                    System.out.println("no");
                    s1 = Server.rs.getString(2);
                    s2 = Server.rs.getInt(3);
                    s3 = Server.rs.getInt(4);
                    System.out.println(s1 + s2 + s3);
                    break;
                }

                if((!s1.isEmpty()) && s2 > 0 && s3 > 0) {
                    Server.fixToStringConsume(s1, ngayLapHoaDon.getText().substring(0,7), Integer.parseInt(maSoDanCu.getText()));
                    Server.fixToOldIntConsume(s2, Integer.valueOf(soCongToCu.getText()));
                    Server.fixToNewIntConsume(s3, Integer.valueOf(soCongToMoi.getText()));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            System.out.println("bye");
        }
    }

    //Tab Three
    public void pressSearch() throws SQLException {
        showInf.clear();
        String s1;
        String s2;
        if(!one.getText().isEmpty()) {
            s1 = one.getText();
            Server.rs = Server.stmt.executeQuery("SELECT * FROM CUSTOMERS WHERE customerName =\'"+ s1 + "\'");

            while (Server.rs.next()) {
                String n1 = "CustomerNumber:\t" + Server.rs.getInt(1);
                String n2 = "CustomerName:\t" + Server.rs.getString(2);
                String n3 = "IdCardNumber:\t" + Server.rs.getString(3);
                String n4 = "Gender:\t" + Server.rs.getString(4);
                String n5 = "Phone:\t" + Server.rs.getInt(5);
                String n6 = "Address:\t" + Server.rs.getString(6);
                String n7 = "BirthDay:\t" + Server.rs.getDate(7);
                String n8 = "StartDay:\t" + Server.rs.getDate(8);
                String n9 = "Type:\t" + Server.rs.getString(9);
                showInf.setText(n1 + "\n" + n2 + "\n" + n3 + "\n" + n4 + "\n" + n5 + "\n"
                                + n6 + "\n" + n7 + "\n" + n8 + "\n" + n9);
            }
        }
        else if(!two.getText().isEmpty()) {
            s2 = two.getText();
            Server.rs = Server.stmt.executeQuery("SELECT * FROM Bills WHERE billNumber =\'"+ s2 + "\'");
            int s3 = 0;
            while (Server.rs.next()) {
                s3 = Server.rs.getInt(2);
            }
            if(s3 != 0) {
                Server.rs = Server.stmt.executeQuery("SELECT * FROM CUSTOMERS WHERE customerName =\'"+ s3 + "\'");
                while (Server.rs.next()) {
                    String n1 = "CustomerNumber:\t" + Server.rs.getInt(1);
                    String n2 = "CustomerName:\t" + Server.rs.getString(2);
                    String n3 = "IdCardNumber:\t" + Server.rs.getString(3);
                    String n4 = "Gender:\t" + Server.rs.getString(4);
                    String n5 = "Phone:\t" + Server.rs.getInt(5);
                    String n6 = "Address:\t" + Server.rs.getString(6);
                    String n7 = "BirthDay:\t" + Server.rs.getDate(7);
                    String n8 = "StartDay:\t" + Server.rs.getDate(8);
                    String n9 = "Type:\t" + Server.rs.getString(9);
                    showInf.setText(n1 + "\n" + n2 + "\n" + n3 + "\n" + n4 + "\n" + n5 + "\n"
                            + n6 + "\n" + n7 + "\n" + n8 + "\n" + n9);
                }
            }
        }
        //test();
    }
    //test funtion
    public void test(){
        showInf.setText("hi\nhi\nhi\n");
    }

    //Tap Four
    public void pressBaoCaoSoDien(){
        String sql = "select * from consume";
        int sumOld = 0;
        int sumNew = 0;
        int count = 0;
        double sum = 0;
        String thang = "";
        try {
            Server.rs = Server.stmt.executeQuery(sql);

            while (Server.rs.next()) {
                sumOld += Server.rs.getInt(3);
                sumNew += Server.rs.getInt(4);
                count ++;
                thang = Server.rs.getString(2);
            }
            sum = (sumNew - sumOld) / count;
            seriesDien.getData().add(new XYChart.Data(thang, sum));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    //public void setLineChart() {
    //    ySoDien.setLabel("số điện trung bình");
    //    xSoDien.setLabel("Tháng");
    //}
    public void pressBaoCaoSoTien(){
        String sql = "select * from consume";
        int sumOld = 0;
        int sumNew = 0;
        int count = 0;
        double sum = 0;
        String thang = "";
        try {
            Server.rs = Server.stmt.executeQuery(sql);

            while (Server.rs.next()) {
                sumOld += Server.rs.getInt(3);
                sumNew += Server.rs.getInt(4);
                count ++;
                thang = Server.rs.getString(2);
            }
            sum = (sumNew - sumOld) / count;

            double Tien = tinhTien(sum);
            int tien = tinhTien(sumNew - sumOld);
            String money = String.valueOf(Tien);

            seriesTien.getData().add(new XYChart.Data(thang, Tien));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addList();
        setVisibleOfAdd(false);
        done.setVisible(false);
        setVisibleOfDeteleView(false);
        doneDelete.setVisible(false);
        doneFix.setVisible(false);
        setFixText(false);
        showOneName.setVisible(false);
        //setLineChart();
        ySoDien.setLabel("số điện trung bình");
        xSoDien.setLabel("Tháng");
        seriesDien.getData().add(new XYChart.Data("bắt đầu", 10));
        lineChartSoDien.getData().add(seriesDien);
        ySoTien.setLabel("Số tiền trung bình");
        xSoTien.setLabel("Tháng");
        seriesTien.getData().add(new XYChart.Data("bắt đầu", 0));
        lineChartSoTien.getData().add(seriesTien);
    }
}
