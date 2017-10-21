/*
 * This file is part of LutConverter library.
 * 
 * Copyright (C) 2017 Freax Software
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package tk.freaxsoftware.utils.lutconverter;

/**
 * General converter error.
 * @author Stanislav Nepochatov
 */
public class ConvertException extends Exception {
    
    public enum Code {
        HALD_LUT_UNSUPPORTED_FORMAT;
    }
    
    private final Code errorCode;

    public ConvertException(Code errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Code getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.name() + ": " + super.getMessage();
    }
    
    
}
