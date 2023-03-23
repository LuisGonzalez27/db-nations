package org.lessons.java;


import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/db_nations";
        String user = "root";
        String password = "root";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            /*
            MILESTONE 1 Scrivere una query SQL che restituisca la lista di tutte le nazioni mostrando nome,
            id, nome della regione e nome del continente, ordinata per nome della nazione.
             */

            String sql = "SELECT c.name AS name_country, c.country_id AS id_country, r.name AS name_region, c2.name AS name_continent \r\n"
                    + "FROM countries c \r\n"
                    + "INNER JOIN regions r \r\n"
                    + "	ON r.region_id = c.region_id \r\n"
                    + "INNER JOIN continents c2 \r\n"
                    + "	ON c2.continent_id = r.continent_id\r\n"
                    + "ORDER BY c.name";

            try (PreparedStatement ps = con.prepareStatement(sql)){

                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        String nameCountry = rs.getString("name_country");
                        int idCountry = rs.getInt("id_country");
                        String nameRegion = rs.getString("name_region");
                        String nameContinent = rs.getString("name_continent");

                        // MILESTONE 2 stampa a video il risultato della Milestone 1
                        System.out.println("Name: " + nameCountry + "\t Id: " + idCountry + "\t Region: " + nameRegion + "\t Continent: " + nameContinent + "\n");

                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
