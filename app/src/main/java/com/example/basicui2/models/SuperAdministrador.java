package com.example.basicui2.models;

import com.example.basicui2.models.Persona;

public class SuperAdministrador extends Persona {
        private String id;
        private String username;
        private String password;

        // Constructor
        public SuperAdministrador(String rut, String nombre, String apellido, String email, String telefono,
                                  String id, String username, String password) {
            super(rut, nombre, apellido, email, telefono);
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
