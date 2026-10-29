package com.msp.webportal.businesslogic.service.idrepo;

import com.msp.webportal.businesslogic.idrepoEntity.Vid;
import com.msp.webportal.businesslogic.idrepoRepository.UinEncryptSaltRepo;
import com.msp.webportal.businesslogic.idrepoRepository.UinHashSaltRepo;
import com.msp.webportal.businesslogic.idrepoRepository.VidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdRepoService {

    public static final String SPLITTER = "_";
    private static final char[] LOOKUP_TABLE_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] LOOKUP_TABLE_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @Autowired
    private UinHashSaltRepo uinHashSaltRepo;

    @Autowired
    private UinEncryptSaltRepo uinEncryptSaltRepo;
    @Autowired
    private VidRepository vidRepository;

    public List<Vid> generateVid(String uin)  {
        List<Vid> vidList = new ArrayList<>();
        try{
            int saltId = getSaltKeyForId(uin);
            System.out.println(saltId);
            String encryptSalt = uinEncryptSaltRepo.retrieveSaltById(saltId);
            System.out.println("encryptSalt: "+encryptSalt);
            String hashSalt = uinHashSaltRepo.retrieveSaltById(saltId);
            System.out.println("HashSalt: "+hashSalt);
            String uinToEncrypt = saltId + SPLITTER + uin + SPLITTER + encryptSalt;
            System.out.println("uinToEncrypt: "+uinToEncrypt);
            String uinHash = String.valueOf(saltId) + SPLITTER
                    + hashwithSalt(uin.getBytes(), decodePlainBase64(hashSalt));
            System.out.println("UinHash: "+ uinHash);
            vidList = vidRepository.findByUinHash(uinHash);
            vidList = vidList.stream()
                    .filter(x -> ! x.getExpiryDTimes().isBefore(LocalDateTime.now())) // Filters out items where `expiryDTimes` is before the current time
                    .collect(Collectors.toList());

        }catch (Exception e){
            e.printStackTrace();
        }
        return vidList;
    }

    public int getSaltKeyForId(String id) {
        Integer saltKeyLength = getIdrepoSaltKeyLength();
        return getIdvidModulo(id, saltKeyLength);
    }
    public static Integer getIdrepoSaltKeyLength() {
        return 3;
    }
    public static final int getIdvidModulo(String idvid, int substrigLen) {
        Assert.isTrue(substrigLen > 0, "divisor should be a positive integer");
        int length = idvid.length();
        return length <= substrigLen ? Integer.parseInt(idvid) : Integer.parseInt(idvid.substring(length - substrigLen));
    }


    public String hashwithSalt(byte[] data, byte[] salt) {
        try {
            return digestAsPlainTextWithSalt(data, salt);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new IdRepoAppUncheckedException(IdRepoErrorConstants.UNKNOWN_ERROR, var4);
        }
        return  null;
    }

    public static String digestAsPlainTextWithSalt(byte[] password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password);
        messageDigest.update(salt);
        return encodeBytesToHex(messageDigest.digest(), true, ByteOrder.BIG_ENDIAN);
    }
    public static String encodeBytesToHex(byte[] byteArray, boolean upperCase, ByteOrder byteOrder) {
        char[] buffer = new char[byteArray.length * 2];
        char[] lookup = upperCase ? LOOKUP_TABLE_UPPER : LOOKUP_TABLE_LOWER;

        for(int i = 0; i < byteArray.length; ++i) {
            int index = byteOrder == ByteOrder.BIG_ENDIAN ? i : byteArray.length - i - 1;
            buffer[i << 1] = lookup[byteArray[index] >> 4 & 15];
            buffer[(i << 1) + 1] = lookup[byteArray[index] & 15];
        }
        return new String(buffer);
    }

    public static byte[] decodePlainBase64(String data) {
        return isNullEmpty(data) ? null : Base64.getDecoder().decode(data);
    }
    public static boolean isNullEmpty(Object obj) {
        return obj == null;
    }






}
