using UnityEngine;

public class SettingsPlugin
{
    public SettingsPlugin()
    {

    }

    public static string GetHomePackage()
    {
        AndroidJNIHelper.debug = true;

        if (Application.platform == RuntimePlatform.Android || true)
        {
            using (AndroidJavaClass jc = new AndroidJavaClass("com.elijahzawesome.homeplugin.AndroidPlugin"))
            {
                Debug.Log(jc.GetHashCode());
                return jc.Call<string>("GetHomeUri");
            }
        } 
        else
        {
            return "currently not running on android.";
        }

        return "error";
    }
}