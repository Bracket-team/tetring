package bracket.tetring.global.login.dto;

import bracket.tetring.domain.player.domain.Player;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(Player player) {
        this.name = player.getName();
        this.email = player.getEmail();
        this.picture = player.getPicture();
    }
}
