package hello.core.singleton;

public class SingletonService {

    // static 변수 생성
    private static SingletonService singletonService = new SingletonService();

    private SingletonService(){

    }

    public static SingletonService getSingletonService(){
        if(singletonService == null){
            singletonService = new SingletonService();
        }
        return singletonService;
    }
}
