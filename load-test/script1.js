import ws from 'k6/ws';
import http from 'k6/http';
import { check, crpyto } from 'k6';
import proto from "./packet_pb.js"

export const options = {
    stages: [
        //    {duration: '30s', target: 100},
        //    {duration: '60s', target: 1000},
        //    {duration: '60s', target: 10},
    ],
    thresholds: {},
    vus: 1,
    duration: '300s'

}
const channelId = "ch_00000001";
const userId = "user_456";

export function setup() {
    const payload = JSON.stringify({
        "userId": userId,
        "channelId": channelId
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };
    const res = http.get(`http://localhost:8180/channel/${channelId}/init?userId=${userId}`, params);
    return res.json();
}

export default (data) => {
    console.log(data)
    let token = data.token;
    let time = data.time;
    let nodeInfo = data.nodeInfo[0];
    const url = `${nodeInfo.protocol}://${nodeInfo.address}:${nodeInfo.port}/ws?cid=${channelId}&uid=${userId}&t=${token}&tm=${time}`;
    console.log("url", url)
    const response = ws.connect(url, {}, function (socket) {
        socket.on('open', function open() {
            console.log('connected');
//            var message = new proto.Message();
//            message.setContent("你好-" + new Date().getTime());
//            message.setType(proto.MessageType.TEXT);
//            console.log(`send message: ${message}`)
//            socket.sendBinary(message.serializeBinary().buffer);
            socket.setInterval(function timeout() {
                socket.ping();
                console.log('Pinging every 1sec (setInterval test)');
            }, 30000);
        });

        socket.on('ping', () => console.log('PING!'));
        socket.on('pong', () => console.log('PONG!'));
        socket.on('message', function (message) {
            console.log(`Received message: ${message}`);
        });
        socket.on('binaryMessage', function (message) {
            console.log(`Received binaryMessage: ${proto.Packet.deserializeBinary(message)}`);
        });
        socket.on('close', () => console.log('disconnected'));

        socket.on('error', (e) => {
            if (e.error() != 'websocket: close sent') {
                console.log('An unexpected error occurred: ', e.error());
            }
        });

    });

    check(response, { 'status is 101': (r) => r && r.status === 101 });
}
