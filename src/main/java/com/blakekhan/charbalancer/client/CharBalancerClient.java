package com.blakekhan.charbalancer.client;

import com.blakekhan.charbalancer.client.proto.BalanceStringRequest;
import com.blakekhan.charbalancer.client.proto.BalanceStringResponse;
import com.blakekhan.charbalancer.client.proto.CharBalancerGrpc;
import com.blakekhan.charbalancer.client.util.Handle;
import com.blakekhan.charbalancer.client.util.HandleStreamObserver;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CharBalancerClient {

  private final ManagedChannel channel;
  private final CharBalancerGrpc.CharBalancerBlockingStub blockingStub;
  private final CharBalancerGrpc.CharBalancerStub asyncStub;

  /**
   * Creates a CharBalancerClient connected to the CharBalancer service at the specified host and port.
   * @param host the host where the charbalancer service is running
   * @param port the port where the charbalancer service is running
   */
  public CharBalancerClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
  }

  /** Construct client for accessing CharBalancer server using the existing channel. */
  public CharBalancerClient(ManagedChannelBuilder<?> channelBuilder) {
    this.channel = channelBuilder.build();
    this.blockingStub = CharBalancerGrpc.newBlockingStub(channel);
    this.asyncStub = CharBalancerGrpc.newStub(channel);
  }

  /**
   * Balances a {@link String}.
   * @param toBalance the string to balance
   * @return {@link BalanceStringRequest} describing the result of the balancing request
   */
  public BalanceStringResponse balanceString(String toBalance) {
    return blockingStub.balanceString(BalanceStringRequest.newBuilder().setData(toBalance).build());
  }

  /**
   * Balances a {@link String} (async).
   * @param toBalance the string to balance
   * @param toHandle the handle to call when the request is complete
   */
  public void balanceString(String toBalance, Handle<BalanceStringResponse> toHandle) {
    asyncStub.balanceString(
        BalanceStringRequest.newBuilder().setData(toBalance).build(),
        new HandleStreamObserver<>(toHandle));
  }
}
