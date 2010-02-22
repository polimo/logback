package ch.qos.logback.core.encoder;

import java.io.ByteArrayOutputStream;

public class ByteArrayUtil {

  // big-endian
  static void writeInt(byte[] byteArray, int offset, int i) {
    for (int j = 0; j < 4; j++) {
      int shift = 24 - j * 8;
      byteArray[offset + j] = (byte) (i >>> shift);
    }
  }

  static void writeInt(ByteArrayOutputStream baos, int i) {
    for (int j = 0; j < 4; j++) {
      int shift = 24 - j * 8;
      baos.write((byte) (i >>> shift));
    }
  }
  
  // big-endian  
  static int readInt(byte[] byteArray, int offset) {
    int i = 0;
    for (int j = 0; j < 4; j++) {
      int shift = 24 - j * 8;
      i += (byteArray[offset + j] & 0xFF) << shift;
    }
    return i;
  }
  
  static public String toHexString(byte[] ba) {
    StringBuffer sbuf = new StringBuffer();
    for(byte b: ba) {
      String s = Integer.toHexString( (int)(b & 0xff));
      if(s.length() == 1) {
        sbuf.append('0');
      }
      sbuf.append(s);
    }
    return sbuf.toString();
  }

  static public byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] ba = new byte[len/2];

    for(int i = 0; i < ba.length; i++) {
      int j = i*2;
      int t = Integer.parseInt(s.substring(j, j+2), 16);
      byte b = (byte) (t & 0xFF);
      ba[i] = b;
    }
    return ba;
  }
}