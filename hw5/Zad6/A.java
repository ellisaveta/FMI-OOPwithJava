public class A extends B implements Fourth {
    void one(First first)
    {
        first.first1();
        first.first2();
    }

    void two(Second second)
    {
        second.second1();
        second.second2();
    }

    void three(Third third)
    {
        third.third1();
        third.third2();
    }

    void four(Fourth fourth)
    {
        fourth.m();
    }
}
