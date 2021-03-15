<template>
    <div>
        <v-container>
            <v-card>
                <v-container>
                    <v-subheadder>회원가입</v-subheadder>
                    <v-form>
                        <v-text-field
                        label="이메일"
                        type="email"
                        required
                        />
                        <v-text-field
                        label="비밀번호"
                        type="password"
                        required
                        />
                        <v-text-field
                        label="비밀번호확인"
                        type="password"
                        required
                        />
                        <v-text-field
                        label="닉네임"
                        type="nickname"
                        required
                        />
                        <v-checkbox
                        required
                        label="당신은 사람입니까?"
                        />
                        <v-btn color="green" type="submit">가입하기</v-btn>
                    </v-form>
                </v-container>
            </v-card>
        </v-container>
    </div>
</template>

<script>
export default{
    data(){
        return{
            name:"Nuext.js",
        }
    },
    head(){
        return{
            title:'회원가입'
        }
    }
}

</script>

<style>
</style>