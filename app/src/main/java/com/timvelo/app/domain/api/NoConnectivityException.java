package com.timvelo.app.domain.api;

import java.io.IOException;

/**
 * Created by admin on 06/06/2017.
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}