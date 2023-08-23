package com.itheima.moves;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class movieRelease {
    /* 键盘录入自动导包。2.键盘扫描对象。*/
    public static final Scanner sc = new Scanner(System.in);
    /* 定义日记对象*/
    public static final Logger LOGGER = LoggerFactory.getLogger("movieRelease.class");

    /* 集合用户,Business  customer容器商家和用户*/
    public static final List<User> lists = new ArrayList<>();
    /* 定义一个商家,存储电影(p1,p2 p3,...)
     * list(p1,p2,...)hashMap如果存储是自定义对象,需要重写equals和hashcode方法
     */
    public static final Map<Merchant, List<Movie>> merchant = new HashMap<>();
    /**
     * 定义一个用户类,存储已看电影(p1,p2 p3,...)
     * list(p1,p2,...)hashMap如果存储是自定义对象,需要重写equals和hashcode方法
     */
    public static final Map<Customer, List<Movie>> customers = new HashMap<>();

    /**
     * 喜欢时间形式
     */
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /* logUser 是新账号*/
    public static User logUser;

    /* 代码块 */
    static {
        //b1,b2  2个用户
        Customer c1 = new Customer();
        c1.setLoginName("用户1");//登录名
        c1.setUserName("小小");
        c1.setPassWord("123456");
        c1.setSex('女');
        c1.setPhone("1256415545");
        c1.setMoney(1000);
        lists.add(c1);
        ArrayList<Movie> Lists = new ArrayList<>();
        customers.put(c1, Lists);


        Customer c2 = new Customer();
        c2.setLoginName("用户2");//登录名
        c2.setUserName("小明");
        c2.setPassWord("123456");
        c2.setSex('女');
        c2.setPhone("1256415545");
        c2.setMoney(1000);
        lists.add(c2);
        List<Movie> list = new ArrayList<>();
        customers.put(c2, list);

        //b1,b2  2个商家
        Merchant b1 = new Merchant();
        b1.setLoginName("商家1"); //登录名
        b1.setUserName("小红");
        b1.setSex('男');
        b1.setPassWord("123456");
        b1.setPhone("13715482545");
        b1.setShopName("中国电影有限公司");
        b1.setMoney(0);
        b1.setAddress("广东阳江");
        lists.add(b1);
        List<Movie> listS = new ArrayList<>();
        merchant.put(b1, listS);

        Merchant b2 = new Merchant();
        b2.setLoginName("商家2");  //登录名
        b2.setUserName("小花");
        b2.setSex('男');
        b2.setPassWord("123456");
        b2.setPhone("13715482584");
        b2.setShopName("国际电影网");
        b2.setMoney(0);
        b2.setAddress("广东茂名");
        lists.add(b2);
        //电影
        List<Movie> List1 = new ArrayList<>();
        merchant.put(b2, List1);
    }

    /*启动包*/
    public static Merchant getSystem() {
        while (true) {
            System.out.println("---------欢迎进入源哥电影系统------------");
            System.out.println("登录输入 1");
            System.out.println("用户注册输入 2");
            System.out.println("商家注册输入 3");
            System.out.println("请输入 1,  2 , 3 命令:");
            String command = sc.nextLine();
            switch (command) {
                case "1" -> GetLogin();
                case "2" ->
                        //用户注册
                        UserRegistration();
                case "3" ->
                        //商家注册
                        MerchantRegistration();
                default -> System.out.println("没有这个命令,请重新输入");
            }

        }

    }

    /* 商家注册首页*/
    private static void MerchantRegistration() {
        //  定义一个商家对象
        try {
            User merchant = new Merchant();
            while (true) {
                System.out.println("-------欢迎小码哥商家注册界面-----------");
                System.out.println("请商家注册账号");
                String UserRegisterLoginName = sc.nextLine();
                //定义一个方法查 账号是否重复
                String UserRegisterName = WhetherTheAccountIsRepeated(UserRegisterLoginName);
                if (UserRegisterName != null) {
                    System.out.println("账号重复,请重新输入");
                    continue;
                }
                //账号不重复 可以用
                merchant.setLoginName(UserRegisterLoginName);
                System.out.println("请真实姓名");//以这个做为忘记密码的开锁
                String username = sc.nextLine();
                merchant.setUserName(username);
                System.out.println("请输入性别");
                String sex = sc.nextLine();
                merchant.setSex(sex.charAt(0));
                System.out.println("请输入电话");
                String phone = sc.nextLine();
                merchant.setPhone(phone);
                //输入商家店铺名
                System.out.println("输入商家店铺名");
                String shopName = sc.nextLine();
                //输入商家店铺地址
                System.out.println("输入商家店铺地址");
                String address = sc.nextLine();
                while (true) {
                    System.out.println("请注册密码");
                    String passWord = sc.nextLine();
                    System.out.println("请重新输入密码");
                    String okPassWord = sc.nextLine();
                    //判断密码是否一致
                    if (okPassWord.equals(passWord)) {
                        merchant.setPassWord(okPassWord);
                        //密码成功
                        lists.add(merchant);
                        Merchant users = (Merchant) merchant;
                        //List添加到merchant集合中
                        List<Movie> List = new ArrayList<>();
                        //商家店铺名.店铺地址添加List集合中
                        users.setShopName(shopName);
                        users.setAddress(address);
                        //business添加到lists集合中
                        lists.add(users);
                        movieRelease.merchant.put(users, List);
                        //把merchant商家当前复制一份
                        logUser = users;
                        //商家展示页面
                        MerchantDisplayPage();
                        return;
                    } else {
                        //  密码不一致
                        System.out.println("密码不一致,请重新输入密码");
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("商家注册首页出现异常");
        }
    }


    /**
     * 用户注册首页
     */
    private static void UserRegistration() {
        //  定义一个用户 对象
        User customerObject = new Customer();
        while (true) {
            System.out.println("-------欢迎小码哥用户注册界面-----------");
            System.out.println("请用户注册账号");
            String UserRegisterLoginName = sc.nextLine();
            //定义一个方法查
            // 账号是否重复
            String UserRegisterName = WhetherTheAccountIsRepeated(UserRegisterLoginName);
            if (UserRegisterName != null) {
                System.out.println("账号重复,请重新输入");
                continue;
            }


            //账号不重复 可以用
            customerObject.setLoginName(UserRegisterLoginName);
            System.out.println("请真实姓名");//以这个做为忘记密码的开锁
            String users = sc.nextLine();
            customerObject.setUserName(users);

            System.out.println("请输入性别");
            String sex = sc.nextLine();
            customerObject.setSex(sex.charAt(0));
            System.out.println("请输入电话");
            String phone = sc.nextLine();
            customerObject.setPhone(phone);
            while (true) {
                System.out.println("请注册密码");
                String passWord = sc.nextLine();
                System.out.println("请重新输入密码");
                String okPassWord = sc.nextLine();
                //判断密码是否一致
                if (okPassWord.equals(passWord)) {
                    customerObject.setPassWord(okPassWord);
                    System.out.println("用户登录成功");

                    Customer customer1 = (Customer) customerObject;
                    lists.add(customer1);

                    //List添加到customers集合中
                    List<Movie> movies = new ArrayList<>();
                    customers.put(customer1, movies);
                    //把Customers用户当前复制一份
                    logUser = customer1;
                    // 用户展示页面
                    UserDisplayPage();
                    return;
                } else {
                    //  密码不一致
                    System.out.println("密码不一致,请重新输入密码");
                }
            }
        }

    }

    /**
     * 用户注册查账号是否重复
     *
     * @param UserRegisterLoginName 用户
     * @return 新用户账号
     */
    private static String WhetherTheAccountIsRepeated(String UserRegisterLoginName) {
        //遍历所有账号
        for (User list : lists) {

            if (list.getLoginName().equals(UserRegisterLoginName)) {
                //    查到账号重复返回
                return list.getLoginName();

            }
        }
        return null;
    }

    /**
     * 登录功能
     */
    private static void GetLogin() {
        System.out.println("-------欢迎小码哥登录界面-----------");
        System.out.println("测试用:账号:商家1 密码:123456     账号:商家2 密码:123456  默认没有电影 需要添加电影 ");
        System.out.println("测试用:账号:用户1 密码:123456     账号:用户2 密码:123456   默认没有电影 需要添加电影");
        try {
            while (true) {
                System.out.println("请输入您的账号");
                String loginName = sc.nextLine();
                //用户查询账号是否存在
                //定义个方法 查账号 u是返回用
                User u = AuditNumber(loginName);
                if (u != null) {
                    System.out.println("账号正确.");
                    // logUser=u 是 新账号
                    logUser = u;
                    break;
                } else {
                    System.out.println("您登录账号不正确还要继续y/n吗?");
                    String command = sc.nextLine();//命令
                    if ("y".equals(command)) {
                        System.out.println("请重新输入账号.");
                    } else {
                        System.out.println("好的");
                        return;
                    }
                }
            }

            while (true) {
                System.out.println("请输入密码");
                String passWord = sc.nextLine();
                //用户 商家一定存在
                if (Objects.equals(logUser.getPassWord(), passWord)) {
                    System.out.println("密码正确");
                    break;
                } else {
                    System.out.println("您登录密码不正确,请重新输入");
                }
            }

            //验证码和密码一致
            //验证码
            while (true) {
                //定义个方法产生 验证码
                String code = VerificationCode.GetVerificationCode(4);
                // 打印验证码
                System.out.println("4位验证码是:" + code);
                System.out.println("请输入验证码  忽略大小写");
                String okVerificationCode = sc.nextLine();
                if (code.equalsIgnoreCase(okVerificationCode)) {
                    System.out.println("验证码正确");
                    System.out.println("密码登录成功");
                    // 用户 商家登录成功
                    if (logUser instanceof Merchant) {
                        //商家展示页面
                        MerchantDisplayPage();
                        return;
                    } else {
                        // 用户展示页面
                        UserDisplayPage();
                        return;
                    }
                } else {
                    System.out.println("验证码不对 请重新录入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * lists查账号
     * loginName登录账号
     */
    private static User AuditNumber(String loginName) {
        for (User user : lists) {
            if (Objects.equals(user.getLoginName(), loginName)) {
                //登录名存在
                return user;
            }
        }
        return null;
    }

    /* 商家展示页面*/
    private static void MerchantDisplayPage() throws Exception {
        while (true) {
            System.out.println("-------欢迎进入小码哥商家------------");
            System.out.println(logUser.getLoginName() + (logUser.getSex() == '男' ? "先生" : "女士") + "你好,请选择商家操作页面");
            System.out.println("1展示详情");
            System.out.println("2.上架电影");
            System.out.println("3.下架电影");
            System.out.println("4.修改电影");
            System.out.println("5.退出");
            String command = sc.nextLine();
            switch (command) {
                case "1" ->
                        //展示电影详情 信息

                        GetHowMovieDetails();
                case "2" ->
                        //上架电影信息
                        GetMovieInformation();
                case "3" ->
                        //下架电影 信息
                        GetTakeDownAMovie();
                case "4" ->
                        //修改电影信息
                        SetMerchantMovie();
                case "5" -> {
                    //退出
                    System.out.println("退出回到首页");
                    return;
                }
                default -> System.out.println("没有这个命令,请重新输入");
            }
        }

    }

    /* 商家修改logUser 商家 电影 信息页面*/
    private static void SetMerchantMovie() {
        while (true) {
            System.out.println("-------修改影片信息页面-----------");
            //商家logUser
            Merchant logUsers = (Merchant) movieRelease.logUser;
            List<Movie> movies = merchant.get(logUsers);
            //修改logUser商家电影信息页面
            System.out.println("请修改那部电影名字");
            String getName = sc.nextLine();
            for (Movie movie : movies) {
                if (movie.getName().contains(getName)) {
                    //要修改的影片movie
                    System.out.println("请修改电影片名");
                    String name = sc.nextLine();
                    System.out.println("请修改电影主演");
                    String actor = sc.nextLine();
                    System.out.println("请修改电影多少分钟");
                    String time = sc.nextLine();
                    System.out.println("请修改电影价格");
                    String price = sc.nextLine();
                    System.out.println("请修改电影票数");
                    String number = sc.nextLine();
                    System.out.println("请修改放映时间  时间格式 2021-2-2 2:2:5");
                    String startTime = sc.nextLine();
                    movie.setName(name);
                    movie.setActor(actor);
                    movie.setTime(Double.parseDouble(time));
                    movie.setPrice(Double.parseDouble(price));
                    movie.setNumber(Integer.parseInt(number));

                    try {
                        movie.setStartTime(sdf.parse(startTime));//放映时间
                        System.out.println("成功修改的影片");
                        GetHowMovieDetails();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.error("您的时间不对,请重新输入");
                    }

                } else {
                    System.out.println("没有影片修改");
                    System.out.println("请问还要继续y/n吗?");
                    String command = sc.nextLine();//命令
                    if ("y".equals(command)) {
                        System.out.println();
                    } else {
                        System.out.println("好的");
                        return;
                    }
                }
            }
        }
    }


    /**
     * 商家下架电影信息
     */
    private static void GetTakeDownAMovie() {

        System.out.println("------下架电影信息操作页面-----------");
        //展示电影详情
        Merchant logUser = (Merchant) movieRelease.logUser;
        List<Movie> Movies = merchant.get(logUser);
        //请输入删除要下架的电影名字
        if (Movies == null) {
            System.out.println("此商家没有影片,请按2上架电影,或按5.退出");
            return;
        }
        System.out.println("请输入要下架的电影名字");
        String name = sc.nextLine();

        for (int i = 0; i < Movies.size(); i++) {
            if (Movies.get(i).getName().contains(name)) {
                //已经下架电影
                Movies.remove(Movies.get(i));
                i--;
                System.out.println("您成功下架电影啦!");
                GetHowMovieDetails();
            } else {
                System.out.println("电影下架不了");
            }
        }
    }


    /* 商家展示logUser电影详情信息*/
    private static void GetHowMovieDetails() {
        System.out.println("---------展示电影详情信息如下-----------");
        Merchant logUsers = (Merchant) movieRelease.logUser;
        System.out.println("登录名:" + logUsers.getLoginName() + "\t\t店铺名称:" + logUsers.getShopName() + "\t\t店铺地址:" + logUsers.getAddress());
        System.out.println("真名:" + logUsers.getUseName() + "\t\t性别:" + logUsers.getSex() + "\t\t电话:" + logUsers.getPhone() + "\t\t余额:" + logUsers.getMoney());

        //根据商家名取值
        List<Movie> movies = merchant.get(logUsers);
        System.out.println("电影片名\t\t\t主演\t\t\t评分\t\t\t分钟\t\t\t票价\t\t\t余票数\t\t\t放映时间");
        for (Movie movie : movies) {
            if (movies.size() > 0) {
                System.out.println(movie.getName() + "\t\t\t" + movie.getActor() + "\t\t\t" + movie.getScore() + "\t\t\t"
                        + movie.getTime() + "\t\t" + movie.getPrice() + "\t\t" + movie.getNumber() + "\t\t\t" +
                        sdf.format(movie.getStartTime()));

            } else {
                System.out.println("商家没有影片可播,请按2.上架影片.或按5.退出.");
            }
        }
    }

    /*商家上架m3电影信息到logUser是新添加当前商家*/
    private static void GetMovieInformation() throws Exception {

        while (true) {
            System.out.println("---------商家上架电影信息---------------");

            //logUser是新添加商家
            Merchant newLogUser = (Merchant) logUser;
            //添加电影
            System.out.println("请添加电影名");
            String addName = sc.nextLine();
            //方法查影片名是否重复
            Movie movie = FindAMovie(addName);
            if (movie != null) {
                //addName已重复
                System.out.println("您影片已重复,请重新输入影片名.");
                continue;
            }

            System.out.println("请添加电影主演");
            String addActor = sc.nextLine();

            System.out.println("请添加电影多少分钟");
            String addTime = sc.nextLine();
            System.out.println("请添加电影价格");
            String price = sc.nextLine();
            System.out.println("请添加电影票数");
            String addNumber = sc.nextLine();
            System.out.println("请添加放映时间 时间格式 2022-02-02 02:02:02");
            String StarTime = sc.nextLine();

            Movie m = new Movie();
            m.setName(addName);
            m.setActor(addActor);
            m.setTime(Double.parseDouble(addTime));
            m.setPrice(Double.parseDouble(price));
            m.setNumber(Integer.parseInt(addNumber));

            m.setStartTime(sdf.parse(StarTime));//放映时间
            List<Movie> movies = merchant.get(newLogUser);
            movies.add(m);
            System.out.println("成功上架一个电影是:" + m.getName());
            // 展示电影
            GetHowMovieDetails();
            return;
        }


    }


    /*用户展示页面*/
    private static void UserDisplayPage() {
        try {
            while (true) {
                System.out.println("您的:" + logUser.getLoginName() + "\t\t\t性别" + logUser.getSex() + "\t\t\t电话" + logUser.getPhone() + "\t\t\t余额" + logUser.getMoney());
                System.out.println("-------欢迎进入小码哥用户------------");
                System.out.println("请选择操作功能");
                System.out.println("1展示全部影片");
                System.out.println("2展示已看影片");
                System.out.println("3.根据电影名称查询电影");
                System.out.println("4.评分功能");
                System.out.println("5.购票功能");
                System.out.println("6.充值功能");
                System.out.println("7.退出系统");
                String command = sc.nextLine();
                switch (command) {
                    case "1" ->
                            //展示全部商家和影片
                            GetAllMovies();
                    case "2" ->
                            //2展示已看影片
                            HaveSeen();
                    case "3" ->
                            //根据电影名查询影片
                            GetQueryMovie();
                    case "4" ->
                            //评分功能
                            ScoringFunction();
                    case "5" ->
                            //购票买票功能
                            PuyTicket();
                    case "6" ->
                            //充值功能
                            TopUp();
                    case "7" -> {
                        //退出系统
                        System.out.println("退出系统欢迎再来~~");
                        return;
                    }
                    default -> System.out.println("没有这个命令,请重新输入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户展示页面异常");
        }
    }

    /* 用户展示已看影片*/
    private static void HaveSeen() {
        System.out.println("-----本用户已看影片有以下-----");
        List<Movie> movies = customers.get(logUser);
        if (movies.size() == 0) {
            System.out.println("没有电影,请买票");
        }
            for (Movie movie : movies) {
                System.out.println("电影片名:" + movie.getName() + "\t\t主演:" + movie.getActor() + "\t\t\t评分:" + movie.getScore() + "\t\t\t时间:"
                        + movie.getTime() + "分钟\t\t票价:" + movie.getPrice() + "\t\t余票数:" + movie.getNumber() + "\t\t\t放映时间:" +
                        sdf.format(movie.getStartTime()));
            }
        }


    /**
     * 用户 评分功能
     */
    private static void ScoringFunction() {
        while (true) {
            System.out.println("----用户评分功能页面--------");
            HaveSeen();
            List<Movie> movies = customers.get(logUser);
            for (Movie movie : movies) {
                System.out.println("给自己电影打分功能");
                System.out.println("请输入已看电影");
                String name = sc.nextLine();
                //展示本用户所有电影名
                if (!movie.getName().contains(name)) {
                    System.out.println("没有此电影");
                    return;
                }
                //输入电影名字和电影名字一样.
                System.out.println("请给自己电影打分0-10分.");
                String score = sc.nextLine();
                //计算一下评分的平均分
                Set<Map.Entry<Merchant, List<Movie>>> entries = merchant.entrySet();
                for (Map.Entry<Merchant, List<Movie>> entry : entries) {
                    List<Movie> value = entry.getValue();
                    //最高价格排序
                    java.util.DoubleSummaryStatistics s = value.stream().
                            collect(Collectors.summarizingDouble(Movie::getScore));
                    movie.setScore(movie.getScore() + Double.parseDouble(score));
                    //平均值
                    double v = BigDecimal.valueOf(s.getAverage()).divide(BigDecimal.valueOf(1),
                            2, RoundingMode.HALF_UP).doubleValue();
                    System.out.println("谢谢您的好评.您评了" + score + "分" + "平均分是:" + v);
                    break;
                }
                System.out.println("请还继续评分吗?y/n");
                String command = sc.nextLine();//命令
                if ("y".equals(command)) {
                    System.out.println();
                } else {
                    System.out.println("好的");
                    return;
                }
            }
        }
    }


    /* 用户 购票 买票功能 */
    private static void PuyTicket() {
        try {
            GetAllMovies();
            System.out.println("------展示购票功能页面-------");
            System.out.println("请输入购买的影片名");
            String name = sc.nextLine();
            //判断有没有该影片
            Movie movie = FindAMovie(name);
            if (movie != null) {
                //用户还可以多少买多少张票?
                System.out.println("提示:影片只剩下" + movie.getNumber() + "张票.");
                while (true) {
                    System.out.println("请您购买的张票");
                    String number = sc.nextLine();
                    //判断movie票数>=要买的票数
                    if (movie.getNumber() >= Integer.parseInt(number)) {
                        //影片总价
                        double allPrime = BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.
                                valueOf(Integer.parseInt(number))).doubleValue();
                        //判断用户余额>=影片总价
                        if (logUser.getMoney() >= allPrime) {

                            //电影总票-要买的票数=剩下多少票数
                            int numberMoney1 = BigDecimal.valueOf(movie.getNumber()).subtract(BigDecimal.valueOf(Integer.parseInt(number))).intValue();
                            //更新电影票数
                            movie.setNumber(numberMoney1);

                            //更新用户余额
                            double money = BigDecimal.valueOf(logUser.getMoney()).subtract(BigDecimal.valueOf(allPrime)).doubleValue();
                            logUser.setMoney(money);

                            //更新商家余额
                            Merchant business = allMerchant(name);

                            assert business != null;
                            business.setMoney(business.getMoney() + allPrime);

                            List<Movie> movies = customers.get(logUser);
                            movies.add(movie);


                            System.out.println("您成功购买了" + number + "张票" + "总价是:" + allPrime + "元钱");
                            System.out.println("成功购买了电影片名:" + movie.getName() + "\t\t\t主演:" + movie.getActor() + "\t\t\t评分:" + movie.getScore() + "\t\t\t分钟:"
                                    + movie.getTime() + "\t\t票价:" + movie.getPrice() + "\t\t余票数:" + movie.getNumber() + "\t\t\t放映时间:" +
                                    sdf.format(movie.getStartTime()));

                            return;

                        } else {
                            System.out.println("余额不足,剩下" + logUser.getMoney() + "元钱,请充值.");
                            System.out.println("请还继续购票吗?y/n");
                            String command = sc.nextLine();//命令
                            if ("y".equals(command)) {
                                TopUp();
                            } else {
                                System.out.println("好的");
                                return;
                            }

                        }
                    } else {
                        System.out.println("此影片票数已满,剩下" + movie.getNumber() + "张票.");
                        System.out.println("请还继续购票吗?y/n");
                        String command = sc.nextLine();//命令
                        if ("y".equals(command)) {
                            System.out.println();
                        } else {
                            System.out.println("好的");
                            return;
                        }
                    }
                }
            } else {
                System.out.println("没该影片");
            }


        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户充值与购票买票功能异常");
        }
    }

    /* 充值功能*/
    private static void TopUp() {
        while (true) {
            System.out.println("------展示充值功能页面-------");
            System.out.println("你当前余额是:" + logUser.getMoney() + "元.请充值");
            System.out.println("请您输入充值金额?");
            String movie = sc.nextLine();
            //当前金额+充值金额
            double money1 = logUser.getMoney() + Double.parseDouble(movie);
            logUser.setMoney(money1);
            System.out.println(logUser.getLoginName() + "您成功充值" + Double.valueOf(movie) + "元钱");
            System.out.println("您当前一共" + money1 + "元钱");
            System.out.println("还要充值吗? y/n");
            String command = sc.nextLine();//命令
            if ("y".equals(command)) {
                System.out.println();
            } else {
                System.out.println("好的");
                return;
            }
        }
    }


    /*用户 展示全部影片 allMovies是全部影片*/
    private static void GetAllMovies() {
        System.out.println("------用户展示全部商家和影片--------");
        Set<Map.Entry<Merchant, List<Movie>>> entries = merchant.entrySet();
        for (Map.Entry<Merchant, List<Movie>> entry : entries) {
            if (entry.getValue() == null) {
                System.out.println("没有影片");
            }
            List<Movie> value = entry.getValue();
            value.sort((o1, o2) -> {
                double time = o2.getScore() - o1.getScore();//评分
                time = time == 0 ? o2.getPrice() - o1.getPrice() : time;//评分一样,按时长排序
                time = time == 0 ? o2.getTime() - o1.getTime() : time;//评分.时长一样,按名字排序
                if (time > 0) {
                    return 1;
                } else if (time < 0) {
                    return -1;
                } else {
                    return 0;
                }
            });

            for (Movie movie : value) {
                if (movie == null) {
                    System.out.println("没有影片");
                }
                assert movie != null;
                System.out.println("电影片名:" + movie.getName() + "\t\t主演:" + movie.getActor() + "\t\t\t评分:" + movie.getScore() + "\t\t\t时间:"
                        + movie.getTime() + "分钟\t\t票价:" + movie.getPrice() + "\t\t余票数:" + movie.getNumber() + "\t\t\t放映时间:" +
                        sdf.format(movie.getStartTime()));

            }
        }

    }


    /*用户 根据电影名查询全部影片*/
    private static void GetQueryMovie() {

        try {
            System.out.println("------根据电影名查询全部影片页面--------");
            System.out.println("请输入电影片名");
            String name = sc.nextLine();

            //根据电影名 查找电影
            //找到影片
            Movie movie = FindAMovie(name);
            if (movie != null) {
                System.out.println("电影片名:" + movie.getName() + "\t\t主演:" + movie.getActor() + "\t\t\t评分:" + movie.getScore() + "\t\t\t时间:"
                        + movie.getTime() + "分钟\t\t票价:" + movie.getPrice() + "\t\t余票数:" + movie.getNumber() + "\t\t\t放映时间:" +
                        sdf.format(movie.getStartTime()));
            } else {
                System.out.println("查找电影不存在.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户 根据电影名查询全部影片异常");

        }

    }


    /*用户根据电影名 找所有电影返回Movie查找电影*/
    private static Movie FindAMovie(String name) {
        Set<Map.Entry<Merchant, List<Movie>>> entries = merchant.entrySet();
        for (Map.Entry<Merchant, List<Movie>> entry : entries) {
            List<Movie> value = entry.getValue();
            if (value == null) {
                System.out.println("没有影片");
            }
            assert value != null;
            for (Movie movie : value) {
                assert name != null;
                if (movie.getName().contains(name)) {
                    return movie;
                }
            }
        }

        return null;
    }

    /*用户 查询所有的商家 返回存在的 Merchant  商家*/
    private static Merchant allMerchant(String name) {
        // 遍历所有的商家和电影
        Set<Map.Entry<Merchant, List<Movie>>> entries = merchant.entrySet();
        for (Map.Entry<Merchant, List<Movie>> entry : entries) {
            //遍历所有的电影
            List<Movie> movies = entry.getValue();
            for (Movie movie : movies) {
                //判断电影是否存在
                if (movie.getName().contains(name)) {
                    //找到商家
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}




