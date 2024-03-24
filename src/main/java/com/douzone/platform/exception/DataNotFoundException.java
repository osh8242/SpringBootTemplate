package com.douzone.platform.exception;

public class DataNotFoundException extends RuntimeException {
    private final String dataType;
    private final String dataName;
    public DataNotFoundException() {
        this.dataType = null;
        this.dataName = null;
    }
    public DataNotFoundException(String dataType, String dataName) {
        this.dataType = dataType;
        this.dataName = dataName;
    }
    public String getMessage(){
        if(this.dataType == null || this.dataName == null) return null;
        return this.dataType + " 타입의 " + this.dataName + "을 찾을 수 없습니다";
    }
}