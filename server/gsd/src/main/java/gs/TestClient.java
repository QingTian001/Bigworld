package gs;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyao on "2021/6/29 12",:02
 */
public class TestClient {


    public static void main(String[] args) throws UnknownHostException {

        String[] ips = new String[] {
//                "47.252.27.68",
//                "47.252.27.68",
//                "47.252.27.68",
//                "47.252.27.68",
//                "47.89.187.133",
//                "47.89.187.133",
//                "47.89.187.133",
//                "47.89.187.133",
//                "47.241.110.25",
//                "47.241.110.25",
//                "47.241.110.25",
//                "47.241.110.25",
//                "47.241.108.209",
//                "47.241.108.209",
//                "47.241.108.209",
//                "47.241.108.209",
//                "47.241.126.149",
//                "47.241.126.149",
//                "47.241.126.149",
//                "47.241.126.149",
//                "47.241.123.234",
//                "47.241.123.234",
//                "47.241.123.234",
//                "47.241.123.234",
//                "47.252.12.251",
//                "47.252.12.251",
//                "47.252.12.251",
//                "47.252.12.251",
//                "47.89.180.0",
//                "47.89.180.0",
//                "47.89.180.0",
//                "47.89.180.0",
//                "47.252.13.58",
//                "47.252.13.58",
//                "47.252.13.58",
//                "47.252.13.58",
//                "47.252.23.14",
//                "47.252.23.14",
//                "47.252.23.14",
//                "47.252.23.14",
//                "47.91.76.18",
//                "47.91.76.18",
//                "47.91.76.18",
//                "47.91.76.18",
//                "47.91.92.150",
//                "47.91.92.150",
//                "47.91.92.150",
//                "47.91.92.150",
//                "8.209.77.96",
//                "8.209.77.96",
//                "8.209.77.96",
//                "8.209.77.96",
//                "8.209.112.31",
//                "8.209.112.31",
//                "8.209.112.31",
//                "8.209.112.31",
//                "47.241.219.214",
//                "47.241.219.214",
//                "47.241.219.214",
//                "47.241.219.214",
//                "47.241.180.215",
//                "47.241.180.215",
//                "47.241.180.215",
//                "47.241.180.215",
//                "47.252.15.121",
//                "47.252.15.121",
//                "47.252.15.121",
//                "47.252.15.121",
//                "47.252.24.237",
//                "47.252.24.237",
//                "47.252.24.237",
//                "47.252.24.237",
//                "47.252.31.108",
//                "47.252.31.108",
//                "47.252.31.108",
//                "47.252.31.108",
//                "47.90.139.130",
//                "47.90.139.130",
//                "47.90.139.130",
//                "47.90.139.130",
//                "47.90.137.133",
//                "47.90.137.133",
//                "47.90.137.133",
//                "47.90.137.133",
//                "47.253.48.131",
//                "47.253.48.131",
//                "47.253.48.131",
//                "47.253.48.131",
//                "47.253.34.51",
//                "47.253.34.51",
//                "47.253.34.51",
//                "47.253.34.51",
//                "47.90.243.21",
//                "47.90.243.21",
//                "47.90.243.21",
//                "47.90.243.21",

                "47.241.108.209",
                "47.241.110.25",
                "47.252.12.251",
                "47.252.13.58",
                "47.252.23.14",
                "47.252.27.68",
                "47.89.180.0",
                "47.89.187.133",
                "47.91.76.18",
                "47.91.92.150",



        };

        int[] ports = new int[] {40012, 40013, 40014, 40015};

        List<InetSocketAddress> addressList = new ArrayList<>();

        for (String ip : ips) {
            for (int port : ports) {
                addressList.add(new InetSocketAddress(ip, port));
            }
        }

//        String ip = "FC00:1::2";
//
        //IPv6Address address = new IPAddressString(""47.89.187.133",").getAddress().toIPv4().getIPv4MappedAddress();

//
//
//
        //Inet6Address inet6Address = (Inet6Address) Inet6Address.getByName(address.toString());
        //System.out.println(address.toString());

        //Inet6Address inet6Address = (Inet6Address)Inet6Address.getByName("fc00:1::2");
        //addressList.add(new InetSocketAddress(inet6Address, 41004));
        //addressList.add(new InetSocketAddress(address.toString(), 40014));

        //InetAddress[] addrs = InetAddress.getAllByName(""172.18.28.33",");

//        for (InetAddress addr : addrs) {
//            System.out.println(addr.getHostAddress());
//        }

//        InetAddress address = InetAddress.getByName(""47.89.187.133",");
//
//        System.out.println(toIpV6(address).toString());
//        addressList.add(new InetSocketAddress(toIpV6(address), 40014));
//
//        //addressList.add(new InetSocketAddress(address, 40014));

        for (InetSocketAddress addr : addressList) {
            try (Socket socket = new Socket()) {

                socket.connect(addr);
                if (socket.isConnected()) {
                }
                System.out.println(addr + " connected");

            } catch (IOException e) {
                System.out.println(addr + " connect failed");
                e.printStackTrace();
            }
        }
    }


    public static InetAddress toIpV6(InetAddress ipAddress) throws UnknownHostException {
        if (ipAddress instanceof Inet6Address)
        {
            return ipAddress;
        }

        byte[] addrs = ipAddress.getAddress();

        byte[] addrV6 = new byte[16];
        addrV6[0] = (byte)0;
        addrV6[1] = (byte)0x64;
        addrV6[2] = (byte)0xff;
        addrV6[3] = (byte)0x9b;
        addrV6[4] = addrV6[5] = addrV6[6] = addrV6[7] = addrV6[8] = addrV6[9] = addrV6[10] = addrV6[11] = (byte)0;
        addrV6[12] = addrs[0];
        addrV6[13] = addrs[1];
        addrV6[14] = addrs[2];
        addrV6[15] = addrs[3];
        return InetAddress.getByAddress(addrV6);
    }
}
