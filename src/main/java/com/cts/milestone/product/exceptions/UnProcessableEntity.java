package com.cts.milestone.product.exceptions;

    public class UnProcessableEntity extends RuntimeException {
        public UnProcessableEntity(String msg){
            super(msg);
        }
        public UnProcessableEntity(String msg, Exception e){
            super(msg,e);
        }
    }


