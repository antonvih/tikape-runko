package tikape.runko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;


//tämä on turha kommentti

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:ruokalista.db");
        AnnosDao annokset = new AnnosDao(database);
        RaakaAineDao raakaAineet = new RaakaAineDao(database);
        AnnosRaakaAineDao annosRaakaAineet = new AnnosRaakaAineDao(database);
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annokset.findAll());

            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.get("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annokset.findAll());

            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/annos/:id", (req, res) -> {
            Integer annosId = Integer.parseInt(req.params(":id"));
            HashMap map = new HashMap<>();
            ArrayList<String> ohjeLista = new ArrayList<>();
            ArrayList<RaakaAine> Rlista = new ArrayList<>();
            ArrayList<AnnosRaakaAine> ARlista = new ArrayList<>();
            map.put("annos", annokset.findOne(annosId));
            map.put("raakaAineet", raakaAineet.findAll());
            Rlista =  (ArrayList<RaakaAine>) raakaAineet.findAllForFood(annosId);
            ARlista = (ArrayList<AnnosRaakaAine>) annosRaakaAineet.findAllFor(annosId);
            for (int i =0; i<Rlista.size(); i++) {
                ohjeLista.add(new String(ARlista.get(i).getJarjestys()+". "+Rlista.get(i).getNimi()+", "+ARlista.get(i).getMaara()+", "+ARlista.get(i).getOhje()));
            }
            Collections.sort(ohjeLista);
            map.put("ohjeet", ohjeLista);
            return new ModelAndView(map, "annos");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.get("/raakaaineet", (req,res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAineet", raakaAineet.findAll());
            return new ModelAndView(map, "RaakaAineet");
        }, new ThymeleafTemplateEngine());

        Spark.post("/raakaaineet", (req, res) -> {
            RaakaAine aine = new RaakaAine(-1, req.queryParams("nimi"));
            raakaAineet.save(aine);
            res.redirect("/raakaaineet");
            return "";

        });

        Spark.post("/annos", (req, res) -> {
            Annos annos = new Annos(-1, Integer.parseInt(req.queryParams("hinta")), req.queryParams("nimi"));
            annokset.save(annos);
            int id = annokset.findIdForNameAndPrice(req.queryParams("nimi"), Integer.parseInt(req.queryParams("hinta"))); //EI TOIMI!
            res.redirect("/annos/"+id);
            return "";
        });
        
        Spark.post("/lisaaAnnosRaakaAine/:id", (req, res) -> {
            int raakaAineId = Integer.parseInt(req.queryParams("raakaAineId"));
            int annosId = Integer.parseInt(req.params(":id"));
            int jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            String maara = req.queryParams("maara");
            String ohje = req.queryParams("ohje");
            AnnosRaakaAine annosRaakaAine = new AnnosRaakaAine(-1, annosId, raakaAineId, jarjestys, maara, ohje);
            annosRaakaAineet.save(annosRaakaAine);
            res.redirect("/annos/"+annosId);
            return "";
        });
 
        
        Spark.get("/ohjeet", (req,res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "ohjeet");
        }, new ThymeleafTemplateEngine());


    }
}
