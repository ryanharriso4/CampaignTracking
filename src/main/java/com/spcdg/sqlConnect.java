package com.spcdg;

import java.sql.*;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class sqlConnect {
    Connection connection = null;

    public sqlConnect() {
        
        Properties properties = new Properties();

        String path = null;
        String user = null;
        String pass = null;

        try (FileInputStream fileInputStream = new FileInputStream("db.config")) {
            properties.load(fileInputStream);
        
            user = properties.getProperty("DBUSER");
            pass = properties.getProperty("DBPASS");
            path = properties.getProperty("DBPATH");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            connection = DriverManager.getConnection(path,
                    user,
                    pass);
        } catch (Exception e) {
            System.err.println("Connection Failed");
        }
    }

    public ArrayList<Piece> loadCharacters(ArrayList<Piece> characters) {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select piece.* from piece where not exists(select 1 from enemy where enemy.pnum=piece.piecenum);");

            while (resultSet.next()) {

                Piece p = new Piece(resultSet.getString("pieceName"), 0, resultSet.getInt("hp"),
                        resultSet.getInt("ac"));
                characters.add(p);
            }

            resultSet = statement.executeQuery("select * from piece inner join enemy on enemy.pnum = piece.piecenum;");
            while (resultSet.next()) {
                Enemy enemy = new Enemy(resultSet.getString("pieceName"), resultSet.getInt("intiativebonus"),
                        resultSet.getInt("hp"), resultSet.getInt("ac"), resultSet.getInt("speed"));

                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2
                        .executeQuery(
                                String.format("select * from otherinfo where infonum = %d;",
                                        resultSet.getInt("inum")));
                resultSet2.next();
                enemy.setOtherInfo(resultSet2.getString("savingthrows"), resultSet2.getString("skills"),
                        resultSet2.getString("vulnerabilites"), resultSet2.getString("resistances"),
                        resultSet2.getString("immunities"), resultSet2.getString("languages"),
                        resultSet2.getString("senses"));

                resultSet2 = statement2.executeQuery(
                        String.format("select * from statblock where statnum = %d;", resultSet.getInt("snum")));
                resultSet2.next();
                enemy.setStatBlock(resultSet2.getString("str"), resultSet2.getString("dex"),
                        resultSet2.getString("con"),
                        resultSet2.getString("intl"), resultSet2.getString("wis"), resultSet2.getString("cha"));

                resultSet2 = statement2.executeQuery(
                        String.format("select * from actionrelation where pnum = %d;",
                                resultSet.getInt("pnum")));

                int anum;
                Statement statement3 = connection.createStatement();
                ResultSet resultSet3;
                ArrayList<String> actions = new ArrayList<>();
                try {
                    while (resultSet2.next()) {

                        anum = resultSet2.getInt("anum");
                        resultSet3 = statement3
                                .executeQuery(String.format("select * from actions where actionnum = %d;",
                                        anum));
                        resultSet3.next();
                        actions.add(resultSet3.getString("action"));
                    }
                    enemy.addActions(actions);
                } catch (Exception e) {
                    System.err.println(e);
                }

                resultSet2 = statement2.executeQuery(
                        String.format("select * from reactionrelation where pnum = %d;",
                                resultSet.getInt("pnum")));
                int rnum;
                ArrayList<String> reactions = new ArrayList<>();
                try {
                    while (resultSet2.next()) {
                        rnum = resultSet2.getInt("rnum");
                        resultSet3 = statement3
                                .executeQuery(String.format("select * from reactions where reactionnum = %d;", rnum));
                        resultSet3.next();
                        reactions.add(resultSet3.getString("reaction"));

                    }
                    enemy.addReactions(reactions);
                } catch (Exception e) {
                    System.err.println(e);
                }

                characters.add(enemy);

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return characters;
    }

    public int addPC(String pieceName, int hp, int ac) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("insert into piece(pieceName, hp, ac) values(\"");
            sb.append(pieceName.trim());
            sb.append("\", ");
            sb.append(hp);
            sb.append(", ");
            sb.append(ac);
            sb.append(");");
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ps.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("select pieceNum from piece where pieceName = \"%s\";",
                            pieceName));
            resultSet.next();
            return resultSet.getInt("pieceNum");

        } catch (Exception e) {
            sb.setLength(0);
            sb.append("The Player Character ");
            sb.append(pieceName);
            sb.append(" already exists in the database");
            throw new IllegalArgumentException(sb.toString());
        }

    }

    public void addEnemy(int speed, String st, String skills, String vul, String res, String immun, String lang,
            String sen, String str, String dex, String con, String intl, String wis, String cha,
            ArrayList<String> actions, ArrayList<String> reactions, String name, int hp, int ac, int ib) {
        try {
            int pcNum = addPC(name, hp, ac);
            int statNum = addStatBlock(str, dex, con, intl, wis, cha);
            int infoNum = addOtherInfo(str, skills, vul, res, immun, lang, sen);

            ArrayList<Integer> actionNums = new ArrayList<>();
            ArrayList<Integer> reactionNums = new ArrayList<>();
            for (String action : actions) {
                actionNums.add(addAction(action));
            }
            for (String reaction : reactions) {
                reactionNums.add(addReaction(reaction));
            }

            PreparedStatement ps = connection.prepareStatement(
                    String.format(
                            "insert into enemy(pNum, iNum, sNum, speed, intiativebonus) values(\"%s\",\"%s\",\"%s\",%d, %d);",
                            pcNum,
                            infoNum, statNum, speed, ib));
            ps.execute();

            for (int num : actionNums) {
                ps = connection.prepareStatement(
                        String.format("insert into actionrelation(pNum, aNum) values(%d, %d);", pcNum, num));
                ps.execute();
            }
            for (int num : reactionNums) {
                ps = connection.prepareStatement(
                        String.format("insert into reactionrelation(pnum, rnum) values(%d, %d);",
                                pcNum, num));
                ps.execute();
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public int addAction(String action) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("insert into actions(action) values(\"");
            sb.append(action.trim());
            sb.append("\");");
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ps.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("select actionNum from actions where action = \"%s\";", action));
            resultSet.next();
            return resultSet.getInt("actionNum");
        } catch (Exception e) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery(String.format("select actionNum from actions where action = \"%s\";", action));
                resultSet.next();
                return resultSet.getInt("actionNum");
            } catch (Exception ex) {
                return 0;
            }

        }
    }

    public int addReaction(String reaction) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("insert into reactions(reaction) values(\"");
            sb.append(reaction.trim());
            sb.append("\");");
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ps.execute();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            String.format("select reactionNum from reactions where reaction = \"%s\";", reaction));
            resultSet.next();
            return resultSet.getInt("reactionNum");
        } catch (Exception e) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery(
                                String.format("select reactionNum from reactions where reaction = \"%s\";", reaction));
                resultSet.next();
                return resultSet.getInt("reactionNum");

            } catch (Exception ex) {
                return 0;

            }
        }
    }

    public int addOtherInfo(String st, String skills, String vul, String res, String immun, String lang, String sen) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(
                    "insert into otherInfo(savingThrows, skills, vulnerabilites, resistances, immunities, languages, senses) values(\"");
            sb.append(st);
            sb.append("\", \"");
            sb.append(skills);
            sb.append("\", \"");
            sb.append(vul);
            sb.append("\", \"");
            sb.append(res);
            sb.append("\", \"");
            sb.append(immun);
            sb.append("\", \"");
            sb.append(lang);
            sb.append("\", \"");
            sb.append(sen);
            sb.append("\");");

            PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select infoNum from otherInfo where savingThrows = \"%s\" and skills = \"%s\" and vulnerabilites = \"%s\" and resistances = \"%s\" and immunities = \"%s\" and languages = \"%s\" and senses = \"%s\";",
                    st, skills, vul, res, immun, lang, sen));
            resultSet.next();
            return resultSet.getInt("infoNum");

        } catch (Exception e) {
            System.err.println(e);
            return 0;
        }
    }

    public int addStatBlock(String str, String dex, String con, String intl, String wis, String cha) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("insert into statblock(str, dex, con, intl, wis, cha) values(\"");
            sb.append(str);
            sb.append("\", \"");
            sb.append(dex);
            sb.append("\", \"");
            sb.append(con);
            sb.append("\", \"");
            sb.append(intl);
            sb.append("\", \"");
            sb.append(wis);
            sb.append("\", \"");
            sb.append(cha);
            sb.append("\");");
            PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select statNum from statBlock where str = \"%s\" and dex = \"%s\" and con = \"%s\" and intl = \"%s\" and wis = \"%s\" and cha = \"%s\";",
                    str, dex, con, intl, wis, cha));
            resultSet.next();
            return resultSet.getInt("statNum");
        } catch (Exception e) {
            return 0;
        }

    }

    public void addEncounter(String name, String loot, String xp, ArrayList<Piece> pieces) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String
                    .format("insert into encounter(encName, loot, xp) value(\"%s\", \"%s\", \"%s\");", name, loot, xp));
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("select encNum from encounter where encName = \"%s\";", name));
            resultSet.next();
            int encNum = resultSet.getInt("encNum");

            for (Piece p : pieces) {
                resultSet = statement
                        .executeQuery(String.format("select * from piece where pieceName = \"%s\";", p.getName()));

                resultSet.next();
                int pieceNum = resultSet.getInt("pieceNum");

                preparedStatement = connection
                        .prepareStatement(String.format("insert into encounterrelations(enum, pnum) values(%d, %d);",
                                encNum, pieceNum));
                preparedStatement.execute();
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void loadEncounters(ArrayList<Piece> pieces, ArrayList<Encounter> encounters) {
        try {

            ArrayList<Piece> piecesCopy = new ArrayList<Piece>(pieces); // pieces is an alias so we want to use this
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from encounter;");
            while (resultSet.next()) {
                int encNum = resultSet.getInt("encNum");
                Encounter encounter = new Encounter(resultSet.getString("encName"), resultSet.getString("loot"),
                        resultSet.getString("xp"));

                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2
                        .executeQuery(String.format("select pnum from encounterrelations where enum = %d;", encNum));

                while (resultSet2.next()) {

                    Statement statement3 = connection.createStatement();
                    ResultSet resultSet3 = statement3
                            .executeQuery(String.format("select piecename from piece where piecenum = %d;",
                                    resultSet2.getInt("pnum")));
                    resultSet3.next();
                    String pieceName = resultSet3.getString("piecename");
                    for (Piece p : piecesCopy) {
                        if (p.getName().equals(pieceName)) {
                            encounter.addCharacter(p);
                            break;
                        }

                    }

                }

                encounters.add(encounter);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void addLocation(String name, ArrayList<Encounter> encounters) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(String.format("insert into location(locName) values(\"%s\");", name));
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(String.format("select locNum from location where locName = \"%s\";", name));
            resultSet.next();

            int locNum = resultSet.getInt("locNum");

            for (Encounter e : encounters) {
                resultSet = statement.executeQuery(
                        String.format("select encnum from encounter where encname = \"%s\";", e.getName()));
                resultSet.next();
                int encNum = resultSet.getInt("encnum");
                preparedStatement = connection.prepareStatement(
                        String.format("insert into locationrelations(lnum, enum) values(%d, %d);", locNum, encNum));
                preparedStatement.execute();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void loadLocations(ArrayList<Encounter> encounters, ArrayList<Location> locations) {
        try {
            ArrayList<Encounter> encountersCopy = new ArrayList<Encounter>(encounters);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from location;");
            while (resultSet.next()) {
                int locNum = resultSet.getInt("locNum");
                Location location = new Location(resultSet.getString("locName"));

                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2
                        .executeQuery(String.format("select enum from locationrelations where lnum = %d;", locNum));

                while (resultSet2.next()) {
                    Statement statement3 = connection.createStatement();
                    ResultSet resultSet3 = statement3
                            .executeQuery(String.format("select encName from encounter where encnum = %d;",
                                    resultSet2.getInt("enum")));
                    resultSet3.next();
                    String encName = resultSet3.getString("encName");
                    for (Encounter e : encountersCopy) {
                        if (e.getName().equals(encName)) {
                            location.addEncounter(e);
                            break;
                        }
                    }
                }

                locations.add(location);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
