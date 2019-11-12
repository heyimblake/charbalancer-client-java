package com.blakekhan.charbalancer.client.util;

import io.grpc.Status;

/**
 * Represents something that can be handled.
 * @param <T> the type of argument that this interface can handle
 */
public interface Handle<T> {

  /**
   * Handles a call with a provided argument.
   * @param t the argument, can be <code>null</code>
   * @param error the error, can be <code>null</code>
   */
  void handle(T t, Status error);
}
