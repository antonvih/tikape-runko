package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;


//tämä on turha kommentti

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:ruokalista.db");

 


    }
}
