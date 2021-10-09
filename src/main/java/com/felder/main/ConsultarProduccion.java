package com.felder.main;

import com.felder.controller.ConsultarController;
import com.felder.model.entity.Pallet;
import com.felder.model.service.PalletServiceImpl;
import java.io.File;
import java.util.Properties;
import javax.swing.UIManager;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class ConsultarProduccion {

    public static String strLogoApp = System.getProperty("user.dir") + File.separator + "src" + File.separator + "com" + File.separator + "felder" + File.separator + "img" + File.separator + "globoMini.png";
    public static String spdf = System.getProperty("user.dir") + "qwe.pdf";

    /*METODO APLICA PROPIEDADES DEL TEMA*/
    private static void tema() {
        Properties props = new Properties();
        try {
            props.put("logoString", "");
            props.put("textShadow", "on");
            props.put("windowDecoration", "on");
            props.put("systemTextFont", "Agency FB BOLD 17");
            props.put("controlTextFont", "Agency FB BOLD 17");
            props.put("menuTextFont", "Agency FB BOLD 17");
            props.put("userTextFont", "Agency FB BOLD 17");
            props.put("subTextFont", "Agency FB BOLD 17");
            props.put("windowTitleFont", "Agency FB BOLD 17");
            com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tema();
//        System.out.println(new PalletServiceImpl().findById(new Pallet(1)).toString());
        new ConsultarController();
    }

}
