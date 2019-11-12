package com.blakekhan.charbalancer.client.util;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class HandleStreamObserver<V> implements StreamObserver<V> {

  private final Handle<V> callback;

  public HandleStreamObserver(Handle<V> callback) {
    this.callback = callback;
  }

  @Override
  public void onNext(V v) {
    callback.handle(v, null);
  }

  @Override
  public void onError(Throwable throwable) {
    callback.handle(null, Status.fromThrowable(throwable));
  }

  @Override
  public void onCompleted() {
    // Empty
  }
}
