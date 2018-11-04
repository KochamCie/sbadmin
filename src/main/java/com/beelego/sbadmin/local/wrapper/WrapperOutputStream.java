package com.beelego.sbadmin.local.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author : hama
 * @since : created in  2018/10/23
 */
public class WrapperOutputStream  extends ServletOutputStream {

    private ByteArrayOutputStream bos;

    public WrapperOutputStream(ByteArrayOutputStream bos) {
        this.bos = bos;
    }

    @Override
    public void write(int b) throws IOException {
        bos.write(b);
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
