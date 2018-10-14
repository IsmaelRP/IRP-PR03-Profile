package es.iessaladillo.ismaelraqi.pr03.data.local;

import java.util.ArrayList;
import java.util.Random;

import es.iessaladillo.ismaelraqi.irp_pr03_profile.R;
import es.iessaladillo.ismaelraqi.pr03.data.local.model.Avatar;

/**
 * Database to retrieve avatars from. It uses singleton design pattern.
 */
// DO NOT TOUCH
public class Database {

    private static Database instance;

    private final ArrayList<Avatar> avatars = new ArrayList<>();
    private final Random random = new Random(1);
    private long count;

    private Database() {
        insertAvatar(new Avatar(R.drawable.cat1, "Tom"));
        insertAvatar(new Avatar(R.drawable.cat2, "Luna"));
        insertAvatar(new Avatar(R.drawable.cat3, "Simba"));
        insertAvatar(new Avatar(R.drawable.cat4, "Kitty"));
        insertAvatar(new Avatar(R.drawable.cat5, "Felix"));
        insertAvatar(new Avatar(R.drawable.cat6, "Nina"));
    }

    /**
     *
     * @return The singleton instance of the database
     */
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("UnusedReturnValue")
    private long insertAvatar(Avatar avatar) {
        long id = count++;
        avatar.setId(id);
        avatars.add(avatar);
        return id;
    }

    /**
     *
     * @return A random avatar from the database.
     */
    public Avatar getRandomAvatar() {
        return avatars.get(random.nextInt(avatars.size()));
    }

    /**
     *
     * @return The avatar to be used as the default one.
     */
    public Avatar getDefaultAvatar() {
        return avatars.get(0);
    }

}