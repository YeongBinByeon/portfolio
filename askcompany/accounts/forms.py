from django import forms
from .models import User
from django.contrib.auth.forms import (
    UserCreationForm, PasswordChangeForm as AuthPasswordChangeForm
)

#UserCreationForm은 유저모델에 대한 모델폼이며 우리가 추가하고자 하는것도 유저모델(장고에서 정의된)에
#  다 있으니까 class meta에다가 UserCreationForm.Meta를 상속받아서 원하는 필드를 추가해주면 된다
class SignupForm(UserCreationForm):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['email'].required = True
        self.fields['first_name'].required = True
        self.fields['last_name'].required = True

    class Meta(UserCreationForm.Meta):
        model = User
        fields = ['username', 'email', 'first_name', 'last_name']

    #email 중복 체크
    #clean_email은 장고 어딘가에 존재하는 함수를 오버라이드 해서 사용하는것으로 추정
    def clean_email(self):
        email = self.cleaned_data.get('email')
        if email:
            qs = User.objects.filter(email=email)
            if qs.exists():
                raise forms.ValidationError("이미 등록된 이메일 주소입니다.")
        return email
        
class ProfileForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['avatar', 'first_name', 'last_name', 'website_url',
                     'bio', 'phone_number', 'gender']

class PasswordChangeForm(AuthPasswordChangeForm):

    # 변경 후 암호가 변경 전 암호와 동일하면 예외 발생 시키는 코드
    def clean_new_password2(self):
        old_password = self.cleaned_data.get('old_password')
        new_password2 = super().clean_new_password2()
        if old_password == new_password2:
            raise forms.ValidationError("새로운 암호는 기존 암호와 다르게 입력해주세요.")
        return new_password2