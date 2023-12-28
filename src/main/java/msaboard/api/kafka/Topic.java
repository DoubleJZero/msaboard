package msaboard.api.kafka;

public enum Topic {
    USER_TOPIC("user-service-topic");

    private String name;

    Topic(String name){
        this.name = name;
    }
}
