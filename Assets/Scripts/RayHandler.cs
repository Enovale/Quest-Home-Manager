using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RayHandler : MonoBehaviour
{

    [Range(0, 10)]
    public float TargetOffset = 1f;
    public Transform Target;
    public Transform OriginPoint;
    public LineRenderer Renderer;

    // Update is called once per frame
    void Update()
    {
        Debug.Assert(Target != null);
        Debug.Assert(OriginPoint != null);
        Debug.Assert(Renderer != null);

        Renderer.SetPositions(new Vector3[] { Target.position + (Target.forward * TargetOffset), OriginPoint.position });
    }
}
