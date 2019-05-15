package com.github.danikleonov.barcodescanner.Model;

public class ListItem2 {
        int id;
        String code;
        String type;
        String clas;
        String descr;

        public ListItem2(int id, String code, String type, String clas, String descr){
            this.id = id;
            this.code = code;
            this.type = type;
            this.clas = clas;
            this.descr = descr;
        }

        public int getId(){
            return id;
        }

        public String getCode(){
            return code;
        }

        public String getType(){
            return type;
        }

        public String getClas(){
            return clas;
        }

        public String getDescr(){
            return descr;
        }
    }

