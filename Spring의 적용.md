## Spring의 적용

### 클래스 정보
* Config
``` java
@ComponentScan("com.fastcampus.spring")
@Configuration
public class Config {
}
```
ㆍ Bean들을 스캔하기 위한 클래스인 Config 클래스를 생성한다.   
ㆍ @ComponentScan 애너테이션을 사용해 기입한 패키지 경로 내 모든 Bean들을 스캔할 수 있도록 설정한다.   
</br>

* BubbleSort
``` java
@Component
public class BubbleSort <T extends Comparable<T>> implements Sort<T> {

    @Override
    public List<T> sort(List<T> list) {
        List<T> output = new ArrayList<>(list);

        for(int i = output.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (output.get(j).compareTo(output.get(j + 1)) > 0) {
                    T temp = output.get(j);
                    output.set(j, output.get(j + 1));
                    output.set(j + 1, temp);
                }
            }
        }

        return output;
    }
}
```
ㆍ BubbleSort 클래스 상단에 @Component 애너테이션을 기입해 Bean으로 등록한다.   
</br>

* JavaSort
``` java
@Component
public class JavaSort <T extends Comparable<T>> implements Sort<T> {

    @Override
    public List<T> sort(List<T> list) {
        List<T> output = new ArrayList<>(list);
        Collections.sort(output);

        return output;
    }
}
```
ㆍ JavaSort 클래스 상단에 @Component 애너테이션을 기입해 Bean으로 등록한다.   
</br>

* SortService
``` java
@Service
public class SortService {

    private Sort<String> sort;

    public SortService(@Qualifier("bubbleSort") Sort<String> sort) {
        this.sort = sort;
        System.out.println("[구현체] : " + sort.getClass().getName());
    }

    public List<String> doSort(List<String> list) {
        return sort.sort(list);
    }
}
```
ㆍ SortService 클래스 상단에 @Service 애너테이션을 기입해 Bean으로 등록한다.   
ㆍ @Qualifier 애너테이션을 사용하여, 주입받을 객체를 지정한다.   
</br>

* Main
``` java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SortService sortService = context.getBean(SortService.class);
        
        System.out.println("[result] : " + sortService.doSort(Arrays.asList(args));
    }
 
}
```
ㆍ AnnotationConfigApplicationContext를 통해 애너테이션 기반으로 작동하는 스프링 컨텍스트를 생성한다.   
ㆍ 스프링 컨텍스트를 통해 Bean으로 등록된 정렬 알고리즘을 호출한다.   
</br>

### 개선 사항
ㆍ 기존 프로젝트에 Spring Framework를 적용하여 Bean 개념을 도입하였다.   
ㆍ Main 로직안에서 스프링 컨텍스트를 생성하고 Bean으로 등록된 정렬 알고리즘을 호출하는 방식으로 구조를 개선하였다.   
ㆍ Bean으로 등록된 정렬 알고리즘을 호출함으로써 new 키워드를 사용하지 않고 구현체를 사용할 수 있게 되었다.   
ㆍ 또한, Main 로직이 어떠한 알고리즘을 사용하는지 몰라도 되는 느슨한 결합의 구조로 개편이 가능했다.
