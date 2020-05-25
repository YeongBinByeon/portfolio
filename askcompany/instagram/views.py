from django.contrib import messages
from django.contrib.auth import get_user_model
from django.contrib.auth.decorators import login_required
from django.shortcuts import get_object_or_404, redirect, render
from .forms import PostForm
from .models import Post

@login_required
def post_new(request):
    if request.method == 'POST':
        form = PostForm(request.POST, request.FILES)
        if form.is_valid():
            # 필수 필드에 대한 값 지정 없이 commit=True 옵션 으로 form.save()가 호출 되면
            # 에러가 발생한다. 그래서 commit=False 옵션 줘서 바로 db에 저장되지 않도록 한다.
            post = form.save(commit=False)
            post.author = request.user
            post.save()
            post.tag_set.add(*post.extract_tag_list()) # many to many 관계
        

            messages.success(request, "포스팅을 저장했습니다.")
            return redirect(post) # TODO get_absolute_url 활용 - Model에 함수 구현되어 있으면 해당 Value의 pk로 url 반환함.
    else:
        form = PostForm()

    return render(request, "instagram/post_form.html", {
        "form" : form,
    })

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    return render(request, "instagram/post_detail.html", {
        "post":post,
    })

def user_page(request, username):
    page_user = get_object_or_404(get_user_model(), username=username, is_active=True) # is_active는 접근 허용된 사람들만 보겠다는 의미
    post_list = Post.objects.filter(author=page_user)
    return render(request, "instagram/user_page.html", {
        "page_user":page_user,
        "post_list":post_list,
    })