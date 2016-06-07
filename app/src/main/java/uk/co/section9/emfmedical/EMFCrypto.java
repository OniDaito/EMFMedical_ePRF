package uk.co.section9.emfmedical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.util.Log;

// Basic RSA Encryption with block_size byte blocks to keep our data safe

public class EMFCrypto{ 
	
	private PublicKey mKey = null;
	private Cipher mEncodeKeyCipher = null;
	private Cipher mEncodeDataCipher = null;

	public void init(Context ctx) {

		// reads the public key stored in a file
		// http://stackoverflow.com/questions/11532989/android-decrypt-rsa-text-using-a-public-key-stored-in-a-file

        /*Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Log.i("CRYPTO","provider: "+provider.getName());
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                Log.i("CRYPTO","  algorithm: "+service.getAlgorithm());
            }
        }*/

		// Start with the RSA we need to encrypt the symmetric key
		BufferedReader br;
		try {

			// read in the emf_medical.pub file
			br = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.emf_medical)));

			List<String> lines = new ArrayList<String>();
			String line = null;

			while ((line = br.readLine()) != null)
				lines.add(line);

			// removes the first and last lines of the file (comments)
			if (lines.size() > 1 && lines.get(0).startsWith("-----") && lines.get(lines.size() - 1).startsWith("-----")) {
				lines.remove(0);
				lines.remove(lines.size() - 1);
			}

			// concats the remaining lines to a single String
			StringBuilder sb = new StringBuilder();
			for (String aLine : lines)
				sb.append(aLine);
			String keyString = sb.toString();

			// converts the String to a PublicKey instance
			byte[] keyBytes;
			try {
				keyBytes = Base64.decode(keyString.getBytes("utf-8"), Base64.DEFAULT);
				X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
				KeyFactory keyFactory;

				keyFactory = KeyFactory.getInstance("RSA");
				mKey = keyFactory.generatePublic(spec);
                // Simulator likes the below line but the device does not :(
				//mEncodeKeyCipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING"); // Not sure this is the most ideal but we should be less than one block

                // Others to try?

                /*RSA/NONE/OAEPWithMD5AndMGF1Padding
                RSA/NONE/OAEPWithSHA1AndMGF1Padding
                RSA/NONE/OAEPWithSHA224AndMGF1Padding
                RSA/NONE/OAEPWithSHA256AndMGF1Padding
                RSA/NONE/OAEPWithSHA384AndMGF1Padding
                RSA/NONE/OAEPWithSHA512AndMGF1Padding */

                mEncodeKeyCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");
                mEncodeKeyCipher.init(Cipher.ENCRYPT_MODE, mKey);

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
			}

		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e1) {
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
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
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

    private static String padString(String source)  {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++) {
            source += paddingChar;
        }

        return source;
    }

    // Perform the actual encoding
    // A decryption routine would look like this
    /*

#!/bin/bash

# Simple bash script to decode the EMF ePRF files using openssl and a few bash commands

head -c 128 $1 > prfkeyfile
openssl rsautl -oaep -decrypt -inkey emf_medical.pem -in prfkeyfile -out prfkey_decrypted
dd bs=128 skip=1 if=$1 of=prf_plus_iv

head -c 128 prf_plus_iv > prfivfile
openssl rsautl -oaep -decrypt -inkey emf_medical.pem -in prfivfile -out iv_decrypted
dd bs=128 skip=1 if=prf_plus_iv of=prf_encrypted

iv=`cat iv_decrypted`
key=`cat prfkey_decrypted`

echo $iv
echo $key

openssl aes-256-cbc -d -in prf_encrypted -out prf_decrypted.txt -iv $iv -K $key

rm iv_decrypted
rm prfkey_decrypted
rm prf_encrypted
rm prf_plus_iv
rm prfivfile
rm prfkeyfile


     */


    public byte[] encode (byte[] data_bytes){

        // First create a new symmetric cipher
        try {
            mEncodeDataCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aeskey = new byte[32]; // 256 bit - Seems to be the max for our AES
            new SecureRandom().nextBytes(aeskey);

            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // TODO - is there a security issue about using the mEncodeDataCipher twice with the same deets?
            SecretKeySpec skeySpec = new SecretKeySpec(aeskey, "AES");

            byte[] encrypted_key = new byte[0];
            byte[] encypted_iv = new byte[0];
            try {
                // Convert to their hex representations here to save us bother later (we hope!)
                encrypted_key = mEncodeKeyCipher.doFinal(toHex(aeskey).getBytes("UTF-8"));
                encypted_iv = mEncodeKeyCipher.doFinal(toHex(iv).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                mEncodeDataCipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }

            // Encrypt the key
            byte[] encrypted_data = mEncodeDataCipher.doFinal(data_bytes);
            int total_size = encrypted_data.length + encrypted_key.length + encypted_iv.length;

            byte [] finalBytes = new byte[total_size ];

            for (int i=0; i <  encrypted_key.length; i++){
                finalBytes[i] = encrypted_key[i];
            }

            for (int i=0; i < encypted_iv.length; i++) {
                finalBytes[i + encrypted_key.length] = encypted_iv[i];
            }

            for (int i= 0; i < encrypted_data.length; i++){
                finalBytes[i + encrypted_key.length + encypted_iv.length] = encrypted_data[i];
            }

            return finalBytes;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
        } catch (BadPaddingException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            Log.d("ERROR", e.getMessage());
        }

        return null;
    }

	/*public byte[] encode(byte[] data_bytes){
		byte[] finalBytes = null;

		try {
			
			//byte[] data_bytes = data.getBytes();
			int pos = 0;
			int block_idx = 0;
			int num_blocks = (int) Math.ceil((double)(data_bytes.length) / block_size);
			byte[][] byte_blocks = new byte[num_blocks][block_size]; // block_size byte blocks for RSA I decided
			byte[][] encrypted_byte_blocks = new byte[num_blocks][]; // block_size byte blocks for RSA I decided
			
			//System.out.println("ByteBlocks: " + num_blocks + " Length: " + data_bytes.length);
			
			while (pos < data_bytes.length){			
				int i =0;
				for (i =0; i < block_size; i++){
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
	}*/
	
			
}
