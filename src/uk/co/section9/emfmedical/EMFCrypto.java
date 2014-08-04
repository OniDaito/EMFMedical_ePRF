package uk.co.section9.emfmedical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

public class EMFCrypto{ 
	
	private PublicKey mKey = null;
	private Cipher mEncodeCipher = null;
	
	public void init(Context ctx) {
				
		// reads the public key stored in a file
	
		// http://stackoverflow.com/questions/11532989/android-decrypt-rsa-text-using-a-public-key-stored-in-a-file
	    BufferedReader br;
		try {
			
			// read in the emf_medical.pub file
			br = new BufferedReader(new InputStreamReader( ctx.getResources().openRawResource(R.raw.emf_medical) ));
		
			List<String> lines = new ArrayList<String>();
			String line = null;
	   
			while ((line = br.readLine()) != null)
			    lines.add(line);
	
			// removes the first and last lines of the file (comments)
		    if (lines.size() > 1 && lines.get(0).startsWith("-----") && lines.get(lines.size()-1).startsWith("-----")) {
		        lines.remove(0);
		        lines.remove(lines.size()-1);
		    }
	
		    // concats the remaining lines to a single String
		    StringBuilder sb = new StringBuilder();
		    for (String aLine: lines)
		        sb.append(aLine);
		    String keyString = sb.toString();
		    Log.d("log", "keyString:"+keyString);
	
		   
		    // converts the String to a PublicKey instance
		    byte[] keyBytes;
			try {
				keyBytes = Base64.decode(keyString.getBytes("utf-8"), Base64.DEFAULT);
			    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			    KeyFactory keyFactory;
		
				keyFactory = KeyFactory.getInstance("RSA");
			
			    mKey = keyFactory.generatePublic(spec);
	
			    mEncodeCipher = Cipher.getInstance("RSA/None/PKCS1Padding","BC");
			    mEncodeCipher.init(Cipher.ENCRYPT_MODE, mKey);
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
	}
	
   public static String toHex(String txt) {
           return toHex(txt.getBytes());
   }
   
   public static String fromHex(String hex) {
           return new String(toByte(hex));
   }
   
   public static byte[] toByte(String hexString) {
           int len = hexString.length()/2;
           byte[] result = new byte[len];
           for (int i = 0; i < len; i++)
                   result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
           return result;
   }

   public static String toHex(byte[] buf) {
           if (buf == null)
                   return "";
           StringBuffer result = new StringBuffer(2*buf.length);
           for (int i = 0; i < buf.length; i++) {
                   appendHex(result, buf[i]);
           }
           return result.toString();
   }
   private final static String HEX = "0123456789ABCDEF";
   private static void appendHex(StringBuffer sb, byte b) {
           sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
   }
   
   private static String padString(String source)
   {
     char paddingChar = ' ';
     int size = 16;
     int x = source.length() % size;
     int padLength = size - x;

     for (int i = 0; i < padLength; i++)
     {
             source += paddingChar;
     }

     return source;
   }

	public byte[] encode(String data){
		byte[] finalBytes = null;

		try {
			
			byte[] data_bytes = data.getBytes();
			int pos = 0;
			int block_idx = 0;
			int num_blocks = (int) Math.ceil((double)(data_bytes.length) / 64.0);
			byte[][] byte_blocks = new byte[num_blocks][64]; // 64 byte blocks for RSA I decided
			byte[][] encrypted_byte_blocks = new byte[num_blocks][]; // 64 byte blocks for RSA I decided
			
			System.out.println("ByteBlocks: " + num_blocks + " Length: " + data_bytes.length);
			
			while (pos < data_bytes.length){			
				int i =0;
				for (i =0; i < 64; i++){
					if (pos + i >= data_bytes.length ){
						break;
					} else {
						byte_blocks[block_idx][i] = data_bytes[pos+i];
					}
				}
				pos += i;
				encrypted_byte_blocks[block_idx] = mEncodeCipher.doFinal(byte_blocks[block_idx]);
				block_idx++;
			}
			
			// Now concat the encrypted byte blocks (probably they are all the same)
			int total_size = 0;
			for (int i=0; i < num_blocks; i++){
				total_size += encrypted_byte_blocks[i].length;
			}
			
			finalBytes = new byte[total_size];
			
			//finalBytes = new byte[encrypted_byte_blocks[0].length];

			
			pos = 0;
			for (int i=0; i < num_blocks; i++){
			//for (int i=0; i < 1; i++){
				for (int j=0; j < encrypted_byte_blocks[i].length; j++)
					finalBytes[pos + j] = encrypted_byte_blocks[i][j];
				pos += encrypted_byte_blocks[i].length;
			}
					
			
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalBytes;
	}
	
			
}
