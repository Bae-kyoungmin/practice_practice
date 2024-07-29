package com.practice.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.practice.demo.brandVO.BrandVO;


@SpringBootApplication
@EnableScheduling
public class PracticeTestApplication {
	private static StringBuilder sharedBuilder = new StringBuilder();
	private static StringBuffer sharedBuffer = new StringBuffer();
	
	public static void main(String[] args) {
		SpringApplication.run(PracticeTestApplication.class, args);
//		int randomNo = (int) (Math.random()*5);
//		String[] menu = {"미다래","이남장", "테이블B", "라멘", "부찌", "쌀국수"};
//		String lunch = menu[randomNo];
//		System.out.println("오늘의 점심 밥 = " + lunch);
		
		// OOM 확인
//        outOfMemory();
		
		// StringBuffer 와 StringBuilder의 차이 
//        Runnable task = () -> {
//            for (int i = 0; i < 1000; i++) {
//                sharedBuilder.append("A"); // Multiple threads are appending to the shared StringBuilder
////                sharedBuffer.append("A"); // Multiple threads are appending to the shared StringBuilder
//            }
//        };
//
//        Thread thread1 = new Thread(task);
//        Thread thread2 = new Thread(task);
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		
		// StingBuffer & StringBuilder 확인
//        System.out.println("Final StringBuilder length: " + sharedBuilder.length());
//        System.out.println("Final StringBuffer length: " + sharedBuffer.length());
        
        // 날짜 확인
//        dateCheck();
        
        // 스트림 확인
//        streamTest();
	}
	
//	@Scheduled(cron = "0 0/1 * * * *")
	public static void goHome() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String nowTime = formatter.format(cal.getTime());
		
		Calendar cal2 = Calendar.getInstance();
		String goHome = "180000";
		cal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(goHome.substring(0, 2)));
		cal2.set(Calendar.MINUTE, Integer.parseInt(goHome.substring(2, 4)));
		cal2.set(Calendar.SECOND, Integer.parseInt(goHome.substring(4, 6)));
		
		String goHomeMinute = Integer.toString(cal2.get(Calendar.MINUTE));
		String goHomeSecond = Integer.toString(cal2.get(Calendar.SECOND));
		
		// 분
		if(Integer.parseInt(goHomeMinute) < 10) {
			goHomeMinute =  "0"+goHomeMinute;
		} else {
			goHomeMinute =  goHomeMinute;
		}
		
		// 초
		if(Integer.parseInt(goHomeSecond) < 10) {
			goHomeSecond =  "0"+goHomeSecond;
		} else {
			goHomeSecond = goHomeSecond;
		}
		
		String goHomeTime = cal2.get(Calendar.HOUR_OF_DAY) + ":" + goHomeMinute + ":" + goHomeSecond;
		
		
		System.out.println("현재 시간 : " + nowTime);
		System.out.println("퇴근 시간 : " + goHomeTime);
		System.out.println("집가고싶다.");
		System.out.println("==================================================");
	}
	
	public static void  tryCatchFinally() throws IOException {
		String path = "C:/";
		String file = "test.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file);
		
		try {
			System.out.println("br =====> " + br);
		} catch (Exception e) {
			System.out.println("error");
		} finally {
			fos.close();
			fis.close();
			br.close();
		} 
	}
	
	public static void tryResource() throws IOException {
		String path = "c";
		String file = "test.txt";
		path.concat(file);
		try(BufferedReader br = new BufferedReader(new FileReader(path)); 
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file)){
			ZipInputStream zis = new ZipInputStream(fis);
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static void outOfMemory() {
		List<Object> list = new ArrayList<>();
		while(true) {
			list.add(new Object());
		}
	}
	
	public static void dateCheck() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		
		System.out.println(year +" "+ month);
	}
	
	public static void streamTest() {
		// Stream 메서드로 처리
		Stream<String> streamData = Stream.of("bird", "fish", "plant");
		List<String> streamDataList = streamData.collect(Collectors.toList());
//		System.out.println(streamDataList);
		
		// 배열 처리
		String[] strArr = {"seoul", "incheon", "bucheon", "suwon", "ansan"};
		List<String> strArrList = Arrays.asList(strArr);
		List<String> strArrStreamList = strArrList.stream()
										.filter(a -> a.startsWith("i") || a.startsWith("a"))
										.map(String::toUpperCase)
										.collect(Collectors.toList());
//		System.out.println(strArrStreamList);
		
		// 리스트 처리
		List<String> myList = Arrays.asList("january", "march", "july", "december");
		List<String> filteredList = myList.stream()
									.filter(a -> a.startsWith("j"))
									.map(String::toUpperCase)
									.collect(Collectors.toList());
		filteredList.add("march");
		// 병렬처리
		List<String> mmyList = myList.stream().parallel().collect(Collectors.toList());
		Boolean parallelChk = myList.stream().parallel().isParallel();
		Stream<String> tream = myList.parallelStream().sequential();
//		System.out.println(filteredList);
		
		// 셋 처리
		Set<String> mySet = new HashSet<>();
		mySet.add("orange");
		mySet.add("pear");
		mySet.add("kiwi");
		
		Set<String> startWithOList = mySet.stream().filter(o -> o.startsWith("o")).collect(Collectors.toSet());
//		System.out.println(startWithOList);
		
		// map 처리
		Map<String, Object> myMap = new HashMap<>();
		myMap.put("animal", "lion");
		myMap.put("star", "orion");
		myMap.put("veg", "carrot");
		
		Map<String, Object> mapStream = myMap.entrySet().stream().filter(a -> a.getValue().toString().startsWith("o")).collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()));
//		System.out.println(mapStream);
		
		// builder()
		Stream<String> builder = Stream.<String>builder().add("test1").add("test2").build();
//		System.out.println(builder.toList());
		
		// generate(Supplier<? extends String> s) -- 
		Stream<String> generate = Stream.generate(() -> "test").limit(3);
//		System.out.println(generate.toList());
		
		// iterate()
		Stream<Integer> iter = Stream.iterate(5, n -> n + 2).limit(10);
//		System.out.println(iter.toList());

		// IntStream / LongStream / DoubleStream 가능
		IntStream is = IntStream.rangeClosed(20, 30);
		int[] arrayIs = is.toArray();
//		System.out.println(Arrays.toString(arrayIs));
				
		LongStream ls = LongStream.range(9, 12);
		long[] arrayLs = ls.toArray();
//		System.out.println(Arrays.toString(arrayLs));
		
		DoubleStream ds = DoubleStream.iterate(1, n -> n + 1).limit(10);
		double[] arrayDs = ds.toArray();
//		System.out.println(Arrays.toString(arrayDs));
		
		// boxStream
		List<Integer> boxList = IntStream.range(1, 10).boxed().parallel().collect(Collectors.toList());
		boolean trueFalse = IntStream.range(1, 10).boxed().parallel().sequential().isParallel();
//		System.out.println(boxList);
//		System.out.println(trueFalse);
		
		// concat - 중복 허용 // skip - 인자 값 이전의 값은 스킵하므로 인자값부터 그 뒤 데이터를 처리
		Stream<String> concat1 = Stream.of("test", "test1");
		Stream<String> concat2 = Stream.of("test3", "test1");
		List<String> concatList = Stream.concat(concat1, concat2).distinct().collect(Collectors.toList());
//		System.out.println(concatList);
		

		// 가공 시작
		List<String> dataList = Stream.of("water","123", "earth", "air", "fire", "fry", "").collect(Collectors.toList());
		
		// filter
		List<String> filteredDataList = dataList.stream().filter(a -> a.endsWith("e")).collect(Collectors.toList());
//		System.out.println("filteredDataList ===> " + filteredDataList);
		// [fire]
		// contains("e") -> e가 포함된 데이터만 호출
		// contentEquals("earth") -> earth와 같은 데이터만 호출(equals도 같은 역할이며 객체 대상임) 
		// isBlank() -> 빈 문자열 또는 공백 문자열을 필터링
		// isEmpty() -> 빈 문자열을 필터링
		// matches("\\d+") -> 정규식을 넣어 정규식에 맞는 데이터만 필터링
		// regionMatches() -> 인자가 4개 들어가며 1. 람다식 a의 인덱스 / 2. a[0]과 비교문자열 / 3. 2의 시작 인덱스 / 4. 1과2에 적용할 길이
		//		List<String> test = dataList.stream().filter(a -> a.regionMatches(0, "fire", 0, 4)).toList(); [fire]
		
		
		// map
		List<String> mappedDataList = dataList.stream().map(String::toUpperCase).collect(Collectors.toList());
		//System.out.println(mappedDataList); // [WATER, 123, EARTH, AIR, FIRE, FRY, ]
		
		// flatMap
		// 이중 리스트의 경우 [[apple, banana], [orange, grape], [watermelon, pineapple]]
		// 플랫맵을 사용해 하나로 만들 듯 평면화한다고 생각하면 됨 [apple, banana, orange, grape, watermelon, pineapple]
		// 이중 리스트에서 리스트로 각각의 값을 꺼내어 만드는 것보다 더 쉽게 처리가 가능함
        List<List<String>> listOflists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "grape"),
                Arrays.asList("watermelon", "pineapple")
        );
        //System.out.println(listOflists);

        List<String> flattenedStream = listOflists.stream()
                .flatMap(List::stream).collect(Collectors.toList());
        //System.out.println(flattenedStream);
		
        // Sort
        // sorted의 디폴트는 asc / Desc의 경우 Comparator를 이용
		List<String> sortedDataList = dataList.stream().sorted().collect(Collectors.toList());
		List<String> sortedDataListDesc = dataList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//		System.out.println(sortedDataList);
//		System.out.println(sortedDataListDesc);
		
		// distinct
		// concat을 통해 2개의 리스트를 하나로 합칠 때 중복된 값에 대해 제거하고 나머지 값을 리턴 
		List<String> addDataList = Stream.of("cloud", "rain", "electric", "water", "earth", "fire").collect(Collectors.toList());
		List<String> concatDataList = Stream.concat(dataList.stream(), addDataList.stream()).collect(Collectors.toList());
//		System.out.println(concatDataList);
		// [water, 123, earth, air, fire, fry, , cloud, rain, electric, water, earth, fire]
		List<String> concatDataList2 = Stream.concat(dataList.stream(), addDataList.stream()).distinct().collect(Collectors.toList());
//		System.out.println(concatDataList2);
		// [water, 123, earth, air, fire, fry, , cloud, rain, electric]
		
		// skip
		// skip()의 인자값 이후의 값부터 데이터를 리턴해줌
		List<String> skipDataList = dataList.stream().skip(5).collect(Collectors.toList());
//		System.out.println(skipDataList);
		// [fry, ]
		
		// peek
		// 기존 스트림 객체의 값을 로그에 찍어보거나 새로운 객체에 복사처리할 때 반복처리 됨
		// 결과에서 볼 수 있듯 peek -> map 순으로 처리되는 것이 아닌 peek이 전부 처리된 후 map이 처리되게 됨
		List<Integer> testList = new ArrayList<>();
		List<Integer> intList = Stream.of(1,3,4,5,6).collect(Collectors.toList());
		List<Integer> peekDataList = intList.stream().peek(testList::add).map(n -> n+2).collect(Collectors.toList());
//		System.out.println(testList);
		// [1, 3, 4, 5, 6]
		System.out.println(peekDataList);
		// [3, 5, 6, 7, 8]
		
		
		// 결과 도출
		// 숫자 연산 작업
		// 기본적인 작업인 더하기, 평균
		// optionalInt 사용 시 값이 없는 경우 empty로 출력되므로 empty에 대한 처리를 해줄 것(.orElse(0) - int 값으로 처리하면 0 출력)
		int[] intArray = {10, 20, 30, 40, 50};
		OptionalInt sum = OptionalInt.of(IntStream.of(intArray).sum());
		OptionalInt max = IntStream.of(intArray).max();
		OptionalDouble avg = IntStream.of(intArray).average();
		OptionalInt min = IntStream.of(intArray).min();
//		System.out.println(sum.getAsInt());
//		System.out.println(max.orElse(0));
//		System.out.println(avg.orElse(0));
//		System.out.println(min);
//		150
//		50
//		30.0
//		OptionalInt[10]
		// ifPresent()
		// 처리한 값이 있을 경우에 대한 추가 로직을 작성함
		// 처리한 값이 null 값인 경우 ifPresent() 안의 인자는 처리되지 않음
		// 고로 null검사를 별도로 하지 않아도 NPE가 발생을 방지할 수 있음
		IntStream.of(intArray).max().ifPresent(value -> {
			int test = value * value;
//			System.out.println(test);
		});
		
		// reduce
		// 인자는 최대 3개까지 처리가 가능함
		// 1개의 경우 계산 처리식
		Optional<Integer> streamNum1 = Stream.of(100, 200, 300, 400, 500).reduce(Integer::sum); // 1500
//		System.out.println(streamNum1);
		
		// 2개의 경우 초기 누산값
		Optional<Integer> streamNum2 = Optional.of(Stream.of(100, 200, 300, 400, 500).reduce(1, Integer::sum)); // 1501
//		System.out.println(streamNum2);
		
		// 3개의 경우 각각 병렬 처리한 값을 합쳐줌
		Optional<Integer> streamNum3 = Optional.of(Stream.of(100, 200, 300, 400, 500).reduce(1, (a, b) -> a+(b+10), Integer::max)); // 1551
//		System.out.println(streamNum3);
		// 병렬처리 역할을 하는 3번째 인자인 combiner의 경우 parallel로 병렬처리 명시가 돼있어야 처리됨
		Optional<Integer> sequentialStreamNum3 = Optional.of(Stream.of(100, 200, 300, 400, 500).reduce(10, Integer::sum, (a,b) -> {System.out.println("sequential -> " + a + " + " + b); return a+b;})); // 1510
//		System.out.println(sequentialStreamNum3);
//		Optional[1510]
		Optional<Integer> parallelStreamNum3 = Optional
				.of(Stream.of(100, 200, 300, 400, 500).parallel().reduce(10, Integer::sum, (a, b) -> {
					/* System.out.println("parallel -> " + a + " + " + b); */return a+b;})); // 1550
//		System.out.println(parallelStreamNum3);
//		parallel -> 110 + 210 -> 100 + 10 / 200 + 10 = 320
//		parallel -> 410 + 510 -> 400 + 10 / 500 + 10 = 920 
//		parallel -> 310 + 920 -> 300 + 10 / 920 = 1230
//		parallel -> 320 + 1230 
//		Optional[1550]
		
		// String으로 처리 시 
		Optional<String> streamStr2 = Stream.of("momo", "gugu", "chichi").reduce((s1, s2) -> s1.toUpperCase() + ", " + s2.toUpperCase());
//		System.out.println(streamStr2.get());
		
		
		// collect 
		// 스트림 객체를 컬렉션 등으로 변환 및 집계할 수 있게 해줌
		List<BrandVO> brandList = Arrays.asList(new BrandVO("fila", 20),
												new BrandVO("nike", 90),
												new BrandVO("adidas", 80),
												new BrandVO("puma", 80),
												new BrandVO("underarmor", 20)
												);
		// toList
		// 각각의 객체를 하나의 list로 변환
		List<String> collectNameList = brandList.stream().map(BrandVO::getName).collect(Collectors.toList());
		List<Integer> collectSinceList = brandList.stream().map(BrandVO::getSince).collect(Collectors.toList());
//		System.out.println(collectNameList);
//		[fila, nike, adidas, puma, underarmor]
//		System.out.println(collectSinceList);
//		[20, 90, 80, 80, 20]
		
		// joining
		// 각각의 객체를 String으로 변환
		// 인자로 구분자, 프리픽스, 서픽스 사용 가능
		String joiningNamesStr = brandList.stream().map(BrandVO::getName).collect(Collectors.joining(", "));
//		System.out.println(joiningNamesStr);
		// fila, nike, adidas, puma, underarmor
		
		// summarizingInt
		// 카운트, 합계, 최소값, 평균, 최대값 구하기
		// 평균의 경우 더블형으로 리턴
		// 각 값을 가져올 땐 .get키값으로 호출 가능
		IntSummaryStatistics isStaticSince = brandList.stream().collect(Collectors.summarizingInt(BrandVO::getSince));
//		System.out.println(isStaticSince);
		// IntSummaryStatistics{count=5, sum=290, min=20, average=58.000000, max=90}
		
		// collectingAndThen
		// collect를 사용하고 난 뒤 후처리할 작업에 대한 코드 추가
		// 주로 수정불가 처리, 로직 추가, 검증, 변환 등의 작업을 함
		Object catList = brandList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
//		System.out.println(catList);
		
		// match
		// 람다식으로 조건을 넣어 or, and, nor 과 같은 작업을 메서드로 처리함
		Boolean allMatchNames = brandList.stream().map(BrandVO::getName).allMatch(n -> n.length() > 6);
		Boolean anyMatchNames = brandList.stream().map(BrandVO::getName).anyMatch(n -> n.length() > 6);
		Boolean noneMatchNames = brandList.stream().map(BrandVO::getName).noneMatch(n -> n.length() > 6);
		
//		System.out.println(allMatchNames);
//		false
//		System.out.println(anyMatchNames);
//		true
//		System.out.println(noneMatchNames);
//		false
		
		// forEach
		// 스트림 객체 내 데이터만큼 반복하여 내부의 출력 또는 추가적인 처리
//		brandList.stream().map(BrandVO::getName).forEach(System.out::print);
		// filanikeadidaspumaunderarmor
		
		List<String> filteredNamesList = new ArrayList<>();
		brandList.stream().map(BrandVO::getName)
						  .map(name -> name.toUpperCase())
						  .forEach(name -> { if(name.length() > 5) { 
							  					filteredNamesList.add(name);
							  				}
						  }) ;
//		System.out.println(filteredNamesList);
		
	}
	
	
	
	
	
	
	
	
	
	
}
