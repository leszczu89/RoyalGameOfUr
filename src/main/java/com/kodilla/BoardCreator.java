package com.kodilla;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BoardCreator {
    private static Rectangle field1, field2, field3, field4, field5, field6, field7, field8, field9;
    private static Rectangle field10, field11, field12, field13, field14, field1a, field2a, field3a;
    private static Rectangle field4a, field13a, field14a;
    private static GridPane grid;


    public static GridPane getGrid() {
        return grid;
    }

    public GridPane boardCreator() {
        grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        grid.add(field1, 4, 1);
        grid.add(field2, 3, 1);
        grid.add(field3, 2, 1);
        grid.add(field4, 1, 1);
        grid.add(field5, 1, 2);
        grid.add(field6, 2, 2);
        grid.add(field7, 3, 2);
        grid.add(field8, 4, 2);
        grid.add(field9, 5, 2);
        grid.add(field10, 6, 2);
        grid.add(field11, 7, 2);
        grid.add(field12, 8, 2);
        grid.add(field13, 8, 1);
        grid.add(field14, 7, 1);
        grid.add(field1a, 4, 3);
        grid.add(field2a, 3, 3);
        grid.add(field3a, 2, 3);
        grid.add(field4a, 1, 3);
        grid.add(field13a, 8, 3);
        grid.add(field14a, 7, 3);


        return grid;
    }

    public BoardCreator() {
        field1=FieldCreator.field();
        field2=FieldCreator.field();
        field3=FieldCreator.field();
        field4=FieldCreator.field();
        field5=FieldCreator.field();
        field6=FieldCreator.field();
        field7=FieldCreator.field();
        field8=FieldCreator.field();
        field9=FieldCreator.field();
        field10=FieldCreator.field();
        field11=FieldCreator.field();
        field12=FieldCreator.field();
        field13=FieldCreator.field();
        field14=FieldCreator.field();
        field1a=FieldCreator.field();
        field2a=FieldCreator.field();
        field3a=FieldCreator.field();
        field4a=FieldCreator.field();
        field13a=FieldCreator.field();
        field14a=FieldCreator.field();
    }

    public static Rectangle getField1() {
        return field1;
    }

    public static void setField1(Rectangle field1) {
        BoardCreator.field1 = field1;
    }

    public static Rectangle getField2() {
        return field2;
    }

    public static void setField2(Rectangle field2) {
        BoardCreator.field2 = field2;
    }

    public static Rectangle getField3() {
        return field3;
    }

    public static void setField3(Rectangle field3) {
        BoardCreator.field3 = field3;
    }

    public static Rectangle getField4() {
        return field4;
    }

    public static void setField4(Rectangle field4) {
        BoardCreator.field4 = field4;
    }

    public static Rectangle getField5() {
        return field5;
    }

    public static void setField5(Rectangle field5) {
        BoardCreator.field5 = field5;
    }

    public static Rectangle getField6() {
        return field6;
    }

    public static void setField6(Rectangle field6) {
        BoardCreator.field6 = field6;
    }

    public static Rectangle getField7() {
        return field7;
    }

    public static void setField7(Rectangle field7) {
        BoardCreator.field7 = field7;
    }

    public static Rectangle getField8() {
        return field8;
    }

    public static void setField8(Rectangle field8) {
        BoardCreator.field8 = field8;
    }

    public static Rectangle getField9() {
        return field9;
    }

    public static void setField9(Rectangle field9) {
        BoardCreator.field9 = field9;
    }

    public static Rectangle getField10() {
        return field10;
    }

    public static void setField10(Rectangle field10) {
        BoardCreator.field10 = field10;
    }

    public static Rectangle getField11() {
        return field11;
    }

    public static void setField11(Rectangle field11) {
        BoardCreator.field11 = field11;
    }

    public static Rectangle getField12() {
        return field12;
    }

    public static void setField12(Rectangle field12) {
        BoardCreator.field12 = field12;
    }

    public static Rectangle getField13() {
        return field13;
    }

    public static void setField13(Rectangle field13) {
        BoardCreator.field13 = field13;
    }

    public static Rectangle getField14() {
        return field14;
    }

    public static void setField14(Rectangle field14) {
        BoardCreator.field14 = field14;
    }

    public static Rectangle getField1a() {
        return field1a;
    }

    public static void setField1a(Rectangle field1a) {
        BoardCreator.field1a = field1a;
    }

    public static Rectangle getField2a() {
        return field2a;
    }

    public static void setField2a(Rectangle field2a) {
        BoardCreator.field2a = field2a;
    }

    public static Rectangle getField3a() {
        return field3a;
    }

    public static void setField3a(Rectangle field3a) {
        BoardCreator.field3a = field3a;
    }

    public static Rectangle getField4a() {
        return field4a;
    }

    public static void setField4a(Rectangle field4a) {
        BoardCreator.field4a = field4a;
    }

    public static Rectangle getField13a() {
        return field13a;
    }

    public static void setField13a(Rectangle field13a) {
        BoardCreator.field13a = field13a;
    }

    public static Rectangle getField14a() {
        return field14a;
    }

    public static void setField14a(Rectangle field14a) {
        BoardCreator.field14a = field14a;
    }


}
