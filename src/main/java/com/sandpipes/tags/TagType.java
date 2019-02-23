package com.sandpipes.tags;

public enum TagType {
    END, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, BYTE_ARRAY, STRING, LIST, COMPOUND, INT_ARRAY, LONG_ARRAY;
    
    public static TagType getType(byte b) {
        switch(b) {
            case 0:
                return END;
            case 1:
                return BYTE;
            case 2:
                return SHORT;
            case 3:
                return INT;
            case 4:
                return LONG;
            case 5:
                return FLOAT;
            case 6:
                return DOUBLE;
            case 7:
                return BYTE_ARRAY;
            case 8:
                return STRING;
            case 9:
                return LIST;
            case 10:
                return COMPOUND;
            case 11:
                return INT_ARRAY;
            case 12:
                return LONG_ARRAY;
            default:
                return null;
        }
    }
    
    public static byte getType(TagType type) {
        switch(type) {
            case END:
                return 0;
            case BYTE:
                return 1;
            case SHORT:
                return 2;
            case INT:
                return 3;
            case LONG:
                return 4;
            case FLOAT:
                return 5;
            case DOUBLE:
                return 6;
            case BYTE_ARRAY:
                return 7;
            case STRING:
                return 8;
            case LIST:
                return 9;
            case COMPOUND:
                return 10;
            case INT_ARRAY:
                return 11;
            case LONG_ARRAY:
                return 12;
            default:
                return -1;
        }
    }
}
