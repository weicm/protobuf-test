// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/service.proto

package cn.jldata;

public final class Service {
  private Service() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf service {@code SearchService}
   */
  public static abstract class SearchService
      implements com.google.protobuf.Service {
    protected SearchService() {}

    public interface Interface {
      /**
       * <code>rpc Search(.SearchRequest) returns (.SearchResponse);</code>
       */
      public abstract void search(
          com.google.protobuf.RpcController controller,
          cn.jldata.Entities.SearchRequest request,
          com.google.protobuf.RpcCallback<cn.jldata.Entities.SearchResponse> done);

    }

    public static com.google.protobuf.Service newReflectiveService(
        final Interface impl) {
      return new SearchService() {
        @java.lang.Override
        public  void search(
            com.google.protobuf.RpcController controller,
            cn.jldata.Entities.SearchRequest request,
            com.google.protobuf.RpcCallback<cn.jldata.Entities.SearchResponse> done) {
          impl.search(controller, request, done);
        }

      };
    }

    public static com.google.protobuf.BlockingService
        newReflectiveBlockingService(final BlockingInterface impl) {
      return new com.google.protobuf.BlockingService() {
        public final com.google.protobuf.Descriptors.ServiceDescriptor
            getDescriptorForType() {
          return getDescriptor();
        }

        public final com.google.protobuf.Message callBlockingMethod(
            com.google.protobuf.Descriptors.MethodDescriptor method,
            com.google.protobuf.RpcController controller,
            com.google.protobuf.Message request)
            throws com.google.protobuf.ServiceException {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.callBlockingMethod() given method descriptor for " +
              "wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return impl.search(controller, (cn.jldata.Entities.SearchRequest)request);
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getRequestPrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getRequestPrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return cn.jldata.Entities.SearchRequest.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getResponsePrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getResponsePrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return cn.jldata.Entities.SearchResponse.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

      };
    }

    /**
     * <code>rpc Search(.SearchRequest) returns (.SearchResponse);</code>
     */
    public abstract void search(
        com.google.protobuf.RpcController controller,
        cn.jldata.Entities.SearchRequest request,
        com.google.protobuf.RpcCallback<cn.jldata.Entities.SearchResponse> done);

    public static final
        com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptor() {
      return cn.jldata.Service.getDescriptor().getServices().get(0);
    }
    public final com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }

    public final void callMethod(
        com.google.protobuf.Descriptors.MethodDescriptor method,
        com.google.protobuf.RpcController controller,
        com.google.protobuf.Message request,
        com.google.protobuf.RpcCallback<
          com.google.protobuf.Message> done) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.callMethod() given method descriptor for wrong " +
          "service type.");
      }
      switch(method.getIndex()) {
        case 0:
          this.search(controller, (cn.jldata.Entities.SearchRequest)request,
            com.google.protobuf.RpcUtil.<cn.jldata.Entities.SearchResponse>specializeCallback(
              done));
          return;
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getRequestPrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getRequestPrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return cn.jldata.Entities.SearchRequest.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getResponsePrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getResponsePrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return cn.jldata.Entities.SearchResponse.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public static Stub newStub(
        com.google.protobuf.RpcChannel channel) {
      return new Stub(channel);
    }

    public static final class Stub extends cn.jldata.Service.SearchService implements Interface {
      private Stub(com.google.protobuf.RpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.RpcChannel channel;

      public com.google.protobuf.RpcChannel getChannel() {
        return channel;
      }

      public  void search(
          com.google.protobuf.RpcController controller,
          cn.jldata.Entities.SearchRequest request,
          com.google.protobuf.RpcCallback<cn.jldata.Entities.SearchResponse> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          cn.jldata.Entities.SearchResponse.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            cn.jldata.Entities.SearchResponse.class,
            cn.jldata.Entities.SearchResponse.getDefaultInstance()));
      }
    }

    public static BlockingInterface newBlockingStub(
        com.google.protobuf.BlockingRpcChannel channel) {
      return new BlockingStub(channel);
    }

    public interface BlockingInterface {
      public cn.jldata.Entities.SearchResponse search(
          com.google.protobuf.RpcController controller,
          cn.jldata.Entities.SearchRequest request)
          throws com.google.protobuf.ServiceException;
    }

    private static final class BlockingStub implements BlockingInterface {
      private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.BlockingRpcChannel channel;

      public cn.jldata.Entities.SearchResponse search(
          com.google.protobuf.RpcController controller,
          cn.jldata.Entities.SearchRequest request)
          throws com.google.protobuf.ServiceException {
        return (cn.jldata.Entities.SearchResponse) channel.callBlockingMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          cn.jldata.Entities.SearchResponse.getDefaultInstance());
      }

    }

    // @@protoc_insertion_point(class_scope:SearchService)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n src/main/resources/service.proto\032!src/" +
      "main/resources/entities.proto2:\n\rSearchS" +
      "ervice\022)\n\006Search\022\016.SearchRequest\032\017.Searc" +
      "hResponseB\016\n\tcn.jldata\210\001\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          cn.jldata.Entities.getDescriptor(),
        }, assigner);
    cn.jldata.Entities.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
