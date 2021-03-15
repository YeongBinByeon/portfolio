<template>
    <div>
        <v-container>
            <div>
                <post-card/>
                <post-card/>
                <post-card/>
                <post-card/>
                <post-card/>
            </div>
        </v-container>
    </div>
    
</template>

<script>
import PostCard from '~/components/PostCard';

    export default{
        components:{
            PostCard,
        },
        data(){
            return{
                name:"Nuext.js",
            }
        },

}

</script>

<style>
</style>