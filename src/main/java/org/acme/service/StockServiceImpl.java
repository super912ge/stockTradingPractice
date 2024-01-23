package org.acme.service;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.WebSocket;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.resources.StockService;

public class StockServiceImpl implements StockService {

    @Inject
    Vertx vertx;

//    @ConfigProperty(name = "finn.api.key")
//    String apiKey;

    private WebSocket webSocket;

    @GET
    @Path("/connect")
    public Response connect() {
//        WebSocketConnectOptions options = new WebSocketConnectOptions()
//                .setHost("ws.finnhub.io")
//                .setPort(443)
//                .setURI("/?token=" + apiKey)
//                .addHeader(HttpHeaders.UPGRADE.toString(), HttpHeaders.WEBSOCKET.toString())
//                .addHeader(HttpHeaders.CONNECTION.toString(), HttpHeaders.UPGRADE.toString());

//        vertx.createHttpClient().webSocket(options, ar -> {
//            if (ar.succeeded()) {
//                webSocket = ar.result();
//
//                // Connection opened -> Subscribe
//                sendSubscribeMessage("AAPL");
//                sendSubscribeMessage("BINANCE:BTCUSDT");
//                sendSubscribeMessage("IC MARKETS:1");
//            }
//        });

        return Response.ok().build();
    }

    @POST
    @Path("/unsubscribe/{symbol}")
    public Response unsubscribe(@PathParam("symbol") String symbol) {
        if (webSocket != null) {
            sendUnsubscribeMessage(symbol);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @ConsumeEvent("websocket-event")
    public void onMessage(String message) {
        System.out.println("Message from server: " + message);
    }

    private void sendSubscribeMessage(String symbol) {
        if (webSocket != null) {
            webSocket.writeTextMessage("{'type':'subscribe', 'symbol':'" + symbol + "'}");
        }
    }

    private void sendUnsubscribeMessage(String symbol) {
        if (webSocket != null) {
            webSocket.writeTextMessage("{'type':'unsubscribe', 'symbol':'" + symbol + "'}");
        }
    }


    @Override
    public double getStockPriceBySymbol(String Symbol) {
        // mock stock price
        return 20 * Math.random();
    }
}
