from django.contrib import messages
from django.contrib.auth.decorators import login_required
from django.shortcuts import redirect, render
from .forms import PostForm

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
            return redirect("/") # TODO get_absolute_url 활용
    else:
        form = PostForm()

    return render(request, "instagram/post_form.html", {
        "form" : form,
    })