# Command Line 게시판 프로젝트

### 명령어 예시
- 게시판 만들기: `/boards/add?name=자유게시판`
- 글쓰기: `/posts/add?boardId=1&title=프로젝트01&content=프로젝트내용`
- 로그인: `/accounts/signin?username=wee&password=1234`

##  주요 기능

### 기본 기능
#### 게시판 기능
- 게시판 만들기
- 게시판 이름 수정하기
- 게시판 삭제하기
- 게시판 목록 보기: 생성한 모든 게시판 확인

#### 게시글 기능
- 글쓰기
- 글 수정하기
- 글 삭제하기
- 글 내용 보기
- 게시판별 글 목록 보기: 특정 게시판의 모든 게시글 목록 조회

#### 회원 기능
- 회원가입: 새로운 사용자 등록
- 로그인/로그아웃: 사용자 인증 관리
- 회원정보 수정: 비밀번호와 이메일 정보 변경
- 회원 탈퇴: 등록된 회원정보 삭제

### 예외처리
####  로그인 관련
- 중복 로그인 방지: 이미 로그인된 상태에서 추가 로그인 시도 차단
- 권한 체크: 로그아웃 상태에서 회원전용 기능 사용 제한

####  게시판/게시글 관련
- 존재하지 않는 게시판 체크: 없는 게시판에 접근 시도 시 예외처리
- 존재하지 않는 게시글 체크: 없는 게시글 수정/삭제 시도 시 예외처리

##  트러블 슈팅

### 1. 로그인 기능 개선기

처음에 ArrayList를 사용해서 회원정보를 저장했는데, 로그인할 때마다 전체 리스트를 순회해야 해서 속도가 너무 느렸습니다.   
특히 회원수가 많아질수록 더 느려지는 것 같았습니다.  
HashMap으로 회원 아이디를 key로 저장하도록 바꿨더니 
즉시 검색이 가능해져서 로그인 속도가 훨씬 빨라졌습니다.   
HashMap의 containsKey()로 중복 가입 체크도 쉽게 할 수 있게 됐습니다.
상황에 맞는 자료구조를 사용하는 게 중요하다는 걸 느꼈습니다.
```
private List<User> users = new ArrayList<>();

for (User user : users) {
if (user.getUsername().equals(username)) {
// 로그인 처리
    }
}

-------------------------

private Map<String, User> users = new HashMap<>();

public User signin(String username, String password, Session session) {
    // username으로 바로 검색 가능!
    User user = users.get(username);
    if (user == null || !user.getPassword().equals(password)) {
        throw new UserNotFoundException("아이디나 비밀번호가 잘못되었습니다.");
    }
    return user;
}
```

### 2. null 체크의 중요성

비회원도 글을 쓸 수 있게 하려다가 NullPointerException 오류가 발생했습니다.   
로그인하지 않은 사용자는 currentUser가 null인데, 이 상태에서 사용자 정보를 가져오려고 해서 생긴 문제인걸 알게되어  
삼항 연산자로 null 체크를 추가해서 해결했습니다.   
currentUser가 null이면 "비회원"으로 표시하고, 아니면 회원 이름을 사용하도록 수정했습니다.  
이를 통해 예외 처리의 중요성을 배웠고, 특히 null 체크는 꼭 필요하다는 걸 알게 됐습니다.  
```
String author = currentUser.getUsername();

---------------------------------------------------

String author = (currentUser != null) ? currentUser.getUsername() : "비회원";

```

### 3. URL 명령어 처리하기

URL 형식의 명령어를 처리하는 방법이 고민이었습니다.   
예를 들어 "/boards/add?name=1번게시판" 같은 입력을 어떻게 나눠서 처리할지 막막했지만
split() 메소드를 사용해서 단계별로 문자열을 나누는 방식으로 해결했습니다.     
먼저 "?"로 경로와 파라미터를 나누고, "/"로 카테고리를 구분하고, "&"로 여러 파라미터를 나누는 식으로 했습니다.   
깔끔하게 URL 명령어를 처리할 수 있게 됐고, 문자열 처리 방법에 대해 많이 배웠습니다.
```
// 1단계: ? 로 기본 분리
String[] urlParts = url.split("\\?", 2);
// 결과: ["/boards/add", "name=자유게시판&type=normal"]

// 2단계: 경로 부분을 / 로 분리
String[] pathParts = urlParts[0].split("/");
// 결과: ["", "boards", "add"]

// 3단계: 파라미터를 & 로 분리하고 = 로 키/값 구분
for (String param : urlParts[1].split("&")) {
String[] keyValue = param.split("=", 2);
params.put(keyValue[0], keyValue[1]);
}
// 결과: params = {"name" -> "자유게시판", "type" -> "normal"}

```

## 느낀 점

자바 문법 공부를 제대로 마치지 못한 채 프로젝트를 시작하게 되어 처음에는 많은 걱정이 있었습니다.   
3일 정도는 프로젝트 대신 자바 문법 책만 보며 고민하다가, 오히려 프로젝트를 직접 진행하면서 배워보자는 마음으로 시작했습니다.  
초반에는 예상대로 많이 막혔지만, 인터넷 자료와 책을 참고하며 하나씩 해결해나갔습니다.   
확실히 이론으로만 공부할 때보다 실제 프로젝트에 적용하면서 배우니 훨씬 더 빠르고 깊이 있게 이해할 수 있었습니다.  
특히 며칠 동안 이해하기 어려웠던 문법 개념들이 실제 코드를 작성하면서 자연스럽게 이해되는 경험을 통해, 이론과 실습을 같이 해보는 방식의 중요성을 깨달았습니다.

