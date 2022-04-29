package test_one;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class IpV4ConverterUtil {

    public int proceedToInt(String ipString, StringProceedingType type) {
        System.out.print(String.format("Convert type - %s. ", type));
        return switch (type) {
                case BYTEBUFFER_SPLIT -> convertByteBuffer(ipString);
                case BYTEBUFFER_IP_ADDRESS_CLASS -> convertIpAddressClass(ipString);
                case BINARY -> convertBinary(ipString);
        };
    }

    public String proceedToString(int ip, IntProceedingType type) {
        System.out.print(String.format("Convert type - %s. ", type));
        return switch (type) {
            case STRING_JOINER_LOOP -> convertIpJoiner(ip);
            case STRING_BUFFER_INT_STREAM -> convertIpIntStream(ip);
            case STRING_LOOP -> convertIpString(ip);
        };
    }

    private int convertBinary (String ip) {
        int result = 0;
        for (byte b : Arrays.stream(ip.split(("\\."))).map(Integer::parseInt).map(Integer::byteValue).toList()) {
            result = result << 8 | (b & 0xFF);
        }
        System.out.println(String.format("Ip convert to int32. Result: %s.", result));
        return result;
    }

    private int convertByteBuffer (String ip) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        List<Integer> integerList = Arrays.stream(ip.split(("\\."))).map(Integer::valueOf).toList();

        for (int i : integerList) {
            byte b = (byte) i;
            byteBuffer.put(b);

        }
        int ipI = byteBuffer.getInt(0);
        System.out.println(String.format("Ip convert to int32. Result: %s.", ipI));
        return ipI;
    }

    private int convertIpAddressClass(String ip) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        try {
            InetAddress address = Inet4Address.getByName(ip);
            byte[] octetsB = address.getAddress();
            for (byte b : octetsB) {
                byteBuffer.put(b);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int ipI = byteBuffer.getInt(0);
        System.out.println(String.format("Ip convert to int32. Result: %s.", ipI));
        return ipI;
    }

    private String convertIpJoiner (int ip) {
        StringJoiner joiner = new StringJoiner(".","","");

        byte[] array = ByteBuffer.allocate(Integer.BYTES).putInt(ip).array();
        for (byte b : array) {
            if (b < 0) {
                joiner.add(String.valueOf(Byte.toUnsignedInt(b)));
            } else {
                joiner.add(String.valueOf(b));
            }
        }
        String result = joiner.toString();
        System.out.print(String.format("Result: %s.", result));
        return result;
    }

    private String convertIpIntStream (int ip) {
        StringBuilder buffer = new StringBuilder(Integer.BYTES);
        byte[] octets = ByteBuffer.allocate(Integer.BYTES).putInt(ip).array();

        IntStream.range(0, octets.length).forEach(i -> {
            byte b = octets[i];
            if (b < 0) {
                buffer.append(Byte.toUnsignedInt(b));
            } else {
                buffer.append(b);
            }

            if (i < octets.length - 1) {
                buffer.append(".");
            }
        });
        String result = buffer.toString();
        System.out.print(String.format("Result: %s .", result));
        return result;
    }

    private String convertIpString(int ip) {
        byte[] octetsB = ByteBuffer.allocate(Integer.BYTES).putInt(ip).array();
        List<String> octetsS = new ArrayList<>();
        for (byte b : octetsB) {
            if (b < 0) {
                octetsS.add(String.valueOf(Byte.toUnsignedInt(b)));
            } else {
                octetsS.add(String.valueOf(b));
            }
        }
        String result = String.join(".", octetsS);
        System.out.print(String.format("Result: %s .", result));
        return result;
    }
}
