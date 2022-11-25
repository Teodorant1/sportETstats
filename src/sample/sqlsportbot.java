package sample;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class sqlsportbot {
    static String USER = "root";
    static String PASS = "root";
    static String DB_URL = "jdbc:mysql://localhost:3306/squeekdb";
    httpfetch httpfetch1 = new httpfetch();


    public sqlsportbot() {
    }

    public void insert_blog_post(String title, String author,
                                 String text) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement =
                conn.prepareStatement
                        ("INSERT INTO sportstats.blogposts ( title , author , text  ) VALUES ( ?, ?, ? )");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, author);
        preparedStatement.setString(3, text);
    }

    public ArrayList<blogpost> pull_blog_posts() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<blogpost> resultHolder = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sportstats.accounts");
        ResultSet rs = preparedStatement.executeQuery();
        while (
                rs.next()) {
            resultHolder.add(new blogpost(rs.getString("title"), rs.getString("author"),
                    rs.getString("text")));
        }
        conn.close();
        return resultHolder;
    }


    public void insert_account(String username, String email, String status, String password, int gameid)
            throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sportstats.accounts ( username , email , status , password , lastgameid ) VALUES ( ?, ?, ?, ? , ?)");

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, status);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, "23740");

        preparedStatement.executeUpdate();
        conn.close();
    }

    public int get_lastgameid(String username, String password) throws SQLException {
        ArrayList<Integer> resultHolder = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sportstats.accounts WHERE username=? AND password=?");

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            resultHolder.add(rs.getInt("lastgameid"));
        }
        conn.close();
        return resultHolder.get(0);
    }

    public void update_lastgameID(String username, String password, int gameid) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE sportstats.accounts SET lastgameid=? WHERE username=? AND password=?");

        preparedStatement.setInt(1, gameid);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);

        preparedStatement.executeUpdate();
        conn.close();
    }


    public Boolean IsAccountPresent(String username,
                                    String password) throws SQLException {
        ArrayList<account> accounts = pull_accounts();

        Boolean boolean1 = false;

        for (int i = 0; i < accounts.size(); i++) {

      //    System.out.println(accounts.get(i).getPassword());
      //    System.out.println(accounts.get(i).getUsername());

            if (Objects.equals(accounts.get(i).getPassword(), password)
                    &&
                    Objects.equals(accounts.get(i).getUsername(), username)) {
                System.out.println(accounts.get(i).getPassword());
                System.out.println(accounts.get(i).getUsername());
             //   boolean1 = true;
                return true;
            }
        }

     //   return boolean1;
    return false;
    }


    public ArrayList<account> pull_accounts() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<account> resultHolder = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sportstats.accounts");
        ResultSet rs = preparedStatement.executeQuery();
        while (
                rs.next()) {
            resultHolder.add(new account(rs.getString("username"),
                    rs.getString("email"), rs.getString("status"),
                    rs.getString("password"), rs.getInt("lastgameid")));
        }
        conn.close();
        return resultHolder;

    }


    public void insertMatch(String gameid, String team1,
                            String team2) throws SQLException, IOException {
        String left = "https://www.legabasket.it/match/";
        String right = "/pbp/";
        String q1 = httpfetch1.Fetch(left + gameid + right + "1");
        String q2 = httpfetch1.Fetch(left + gameid + right + "2");
        String q3 = httpfetch1.Fetch(left + gameid + right + "3");
        String q4 = httpfetch1.Fetch(left + gameid + right + "4");

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sportstats.rawsportsdata" +
                "(gameID , team1, team2 , q1 , q2 , q3 , q4) VALUES ( ?, ?, ?, ?, ?, ?, ? )");
        preparedStatement.setString(1, gameid);
        preparedStatement.setString(2, team1);
        preparedStatement.setString(3, team2);
        preparedStatement.setString(4, q1);
        preparedStatement.setString(5, q2);
        preparedStatement.setString(6, q3);
        preparedStatement.setString(7, q4);

        preparedStatement.executeUpdate();
        conn.close();
    }

    public Match pullMatch(String gameID, String team1, String team2) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<Match> resultHolder = new ArrayList();
        Statement stmt = conn.createStatement();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sportstats.rawsportsdata WHERE gameID = ?");
        preparedStatement.setString(1, gameID);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            resultHolder.add(new Match(
                    rs.getString("q1"),
                    rs.getString("q2"),
                    rs.getString("q3"),
                    rs.getString("q4"),
                    rs.getString("team1"),
                    rs.getString("team2"),
                    rs.getString("gameID")));
        }
        conn.close();
        return resultHolder.get(0);
    }
}
