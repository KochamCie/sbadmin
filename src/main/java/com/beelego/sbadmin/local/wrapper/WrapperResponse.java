package com.beelego.sbadmin.local.wrapper;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author : hama
 * @since : created in  2018/10/23
 */

public class WrapperResponse  extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;

    public WrapperResponse(HttpServletResponse response) throws UnsupportedEncodingException {
        super(response);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }


}
