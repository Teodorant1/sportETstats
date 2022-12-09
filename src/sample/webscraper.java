package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class webscraper {
    ArrayList<String> teamEndpoints = new ArrayList();

    // String url, String team, int year
    public void getPage(String url1) {
    // String url1 = "https://www.legabasket.it/lba/squadre/2022/1543/openjobmetis-varese/match_schedule";


        try {
            final Document document = Jsoup.connect(url1).get();
            for (Element row : document.select("table.table-hover.table-slim.align-middle.text-center.table-calendario.match-schedule.no-sticky tr")) {
                Elements scorebox = row.select("td.result.big-shoulders");
                String score = scorebox.text();
//              System.out.println("X"+score+"X");
                Elements a = row.select("td.small");
                Elements b = a.select("a.text-decoration-none");
                String url = b.attr("href");
                String[] spliturl = url.split("/");

                //   System.out.println(url);
                Elements c = b.select("span");
                Element d = c.last();

                if (d != null) {
                    String gameID = spliturl[2];
                    // System.out.println(spliturl[2]);
                    String fulldate = d.text();
                    String[] splitdate = fulldate.split("/");
                    String yearhour = splitdate[2];
                    String[] secondsplit = yearhour.split(" ");
                    String year = secondsplit[0];
                 // System.out.println(year);
                    if (year.equals("2022") && !score.equals("0 - 0"))
                    {teamEndpoints.add(gameID);
                  //  System.out.println(gameID);
                    }}


            }

            //  System.out.println(document.select("table.table-hover.table-slim.align-middle.text-center.table-calendario.match-schedule.no-sticky tr "));

//System.out.println(document.outerHtml());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//    System.out.println(teamEndpoints);
    }

    public ArrayList<String> getTeamEndpoints() {
        return teamEndpoints;
    }

    public void setTeamEndpoints(ArrayList<String> teamEndpoints) {
        this.teamEndpoints = teamEndpoints;
    }

}
