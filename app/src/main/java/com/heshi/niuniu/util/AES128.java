package com.heshi.niuniu.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 */

public class AES128 {

    /**
     * 加密到Hex文本
     *
     * @param content
     * @param password
     * @return
     */
    public static String encryptStr(String content, String password) {
        return encrypt(content, password);
    }

    /**
     * 文本解密
     *
     * @param hexChars
     * @param password
     * @return
     */
    public static String decryptStr(String hexChars, String password) {
        return decrypt(hexChars, password);
    }

//    /**
//     * 加密到字节
//     *
//     * @param content
//     * @param password
//     * @return
//     */
//    public static byte[] encrypt(String content, String password) {
//        try {
//            //"AES"：请求的密钥算法的标准名称
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            //256：密钥生成参数；securerandom：密钥生成器的随机源
//            SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
//            kgen.init(256, securerandom);
//            //生成秘密（对称）密钥
//            SecretKey secretKey = kgen.generateKey();
//            //返回基本编码格式的密钥
//            byte[] enCodeFormat = secretKey.getEncoded();
//            //根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            //将提供程序添加到下一个可用位置
//            Security.addProvider(new BouncyCastleProvider());
//            //创建一个实现指定转换的 Cipher对象，该转换由指定的提供程序提供。
//            //"AES/ECB/PKCS7Padding"：转换的名称；"BC"：提供程序的名称
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
//
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] byteContent = content.getBytes("utf-8");
//            byte[] cryptograph = cipher.doFinal(byteContent);
//            return Base64.encode(cryptograph);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 字节解密
//     *
//     * @param cryptograph
//     * @param password
//     * @return
//     */
//    public static String decrypt(byte[] cryptograph, String password) {
//        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
//            kgen.init(256, securerandom);
//            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Security.addProvider(new BouncyCastleProvider());
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
//
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            byte[] content = cipher.doFinal(Base64.decode(cryptograph));
//            return new String(content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content, String pwd) {

        try {
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, genKey(pwd));// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public static String decrypt(String content, String pwd) {

        try {
            byte[] decryptFrom = parseHexStr2Byte(content);
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, genKey(pwd));// 初始化
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据密钥获得 SecretKeySpec
     *
     * @return
     */
    private static SecretKeySpec genKey(String pwd) {
        byte[] enCodeFormat = {0};
        ;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(pwd.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            enCodeFormat = secretKey.getEncoded();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SecretKeySpec(enCodeFormat, "AES");
    }

    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static byte[] tohash256Deal(String datastr) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(datastr.getBytes());
            byte[] hex = digester.digest();
            return hex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

//        String content = "hello world~";
//        System.out.println("明文：" + content);
//        System.out.println("key：" + password);
//
//        byte[] encryptResult = AES256.encrypt(content, password);
//        String cache=AES256.parseByte2HexStr(encryptResult);
//        System.out.println("密文：" +cache );
//
//        String decryptResult = AES256.decrypt(parseHexStr2Byte(cache), password);
//        System.out.println("解密：" + decryptResult);
        String password = "noah2017+";
        boolean isFrist = true;
        Scanner scanner = new Scanner(System.in);
        int State = 0;//0 设置秘钥，1 加密内容 2解密内容
        System.out.print("-------------------------------------------------------------\n" +
                "setpwd:设置秘钥\tencode:加密模式\tdecode:解密模式\texit:退出\n" +
                "-------------------------------------------------------------\n");
        while (true) {
//            System.out.println((isFrist ? "" : "-------------------------------------------------------------\n") +
//                    "当前模式：" + DataUtil.caseData(State,
//                    "密码设置模式", "加密模式", "解密模式") +
//                    "\n-------------------------------------------------------------");
            isFrist = false;
            String input = scanner.next();
            if ("setpwd".equals(input)) {
                State = 0;
                continue;
            } else if ("encode".equals(input)) {
                State = 1;
                System.out.println("已进入加密模式..");
                continue;
            } else if ("decode".equals(input)) {
                State = 2;
                System.out.println("已进入解密模式..");
                continue;
            } else if ("exit".equals(input)) {
                System.out.println("谢谢使用.");
                break;
            } else {
                if (State == 0) {
                    password = input;
                    System.out.println("设置秘钥成功..");
                    State = 1;
                    continue;
                } else if (State == 1) {
                    System.out.println("正在加密中...");
                    System.out.println("加密完成.\n" + encryptStr(input, password));
                } else if (State == 2) {
                    System.out.println("正在解密中...");
                    System.out.println("解密完成.\n" + decryptStr(input, password));
                }
            }
        }
    }
}
