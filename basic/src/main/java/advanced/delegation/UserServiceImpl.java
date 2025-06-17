package advanced.delegation;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-16 15:49
 */
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("保存用户数据");
    }
}
