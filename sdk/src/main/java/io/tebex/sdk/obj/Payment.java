package io.tebex.sdk.obj;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private final int id;
    private final String amount;
    private final String date;
    private final String currency;
    private final String gateway;
    private final String status;
    private final String email;
    private final Player player;
    private final List<Package> packages;
    private final List<Note> note;
    private final String creator_code;

    public Payment(int id, String amount, String date, String currency, String gateway, String status, String email, Player player, List<Package> packages, List<Note> note, String creator_code) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.currency = currency;
        this.gateway = gateway;
        this.status = status;
        this.email = email;
        this.player = player;
        this.packages = packages;
        this.note = note;
        this.creator_code = creator_code;
    }

    public static Payment fromJsonObject(JsonObject jsonObject) {
        JsonArray packagesJsonArray = jsonObject.get("packages").getAsJsonArray();
        List<Package> packages = new ArrayList<>();
        for (JsonElement packageElement : packagesJsonArray) {
            JsonObject packageJson = packageElement.getAsJsonObject();
            Package aPackage = new Package(
                    packageJson.get("id").getAsInt(),
                    packageJson.get("name").getAsString()
            );
            packages.add(aPackage);
        }

        JsonArray notesJsonArray = jsonObject.get("notes").getAsJsonArray();
        List<Note> notes = new ArrayList<>();
        for (JsonElement noteElement : notesJsonArray) {
            JsonObject noteJson = noteElement.getAsJsonObject();
            Note note = new Note(
                    noteJson.get("created_at").getAsString(),
                    noteJson.get("note").getAsString()
            );
            notes.add(note);
        }

        JsonObject playerJson = jsonObject.get("player").getAsJsonObject();
        Player player = new Player(
                playerJson.get("id").getAsInt(),
                playerJson.get("name").getAsString(),
                playerJson.get("uuid").getAsString()
        );

        return new Payment(
                jsonObject.get("id").getAsInt(),
                jsonObject.get("amount").getAsString(),
                jsonObject.get("date").getAsString(),
                jsonObject.get("currency").getAsJsonObject().get("iso_4217").getAsString(),
                jsonObject.get("gateway").getAsJsonObject().get("name").getAsString(),
                jsonObject.get("status").getAsString(),
                jsonObject.get("email").getAsString(),
                player,
                packages,
                notes,
                jsonObject.get("creator_code").getAsString()
        );
    }

    public static class Note {
        private String created_at;
        private String note;

        public Note(String created_at, String note) {
            this.created_at = created_at;
            this.note = note;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getNote() {
            return note;
        }
    }

    public static class Player {
        private int id;
        private String name;
        private String uuid;

        public Player(int id, String name, String uuid) {
            this.id = id;
            this.name = name;
            this.uuid = uuid;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUuid() {
            return uuid;
        }
    }

    public static class Package {
        private final int id;
        private final String name;

        public Package(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}