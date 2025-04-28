package edu.ntnu.irr.bidata.Model.LadderGame.Event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = LadderEvent.class, name = "LadderEvent"),
    @JsonSubTypes.Type(value = QizzEvent.class, name = "QizzEvent")
})

public abstract class Event {

  public abstract int Action();
}
