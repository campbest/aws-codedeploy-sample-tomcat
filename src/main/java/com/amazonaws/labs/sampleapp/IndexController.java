package com.amazonaws.labs.sampleapp;

@Controller
public class IndexController {
    private final static Logger LOGGER = Logger.getLogger(IndexController.class.getName());

    @Value("${APPLICATION_NAME}")

    @Value("${DEPLOYMENT_GROUP_NAME}")
    private String deploymentGroupName;

    @Autowired
    private AmazonCodeDeploy codeDeploy;

    @Autowired

    @Autowired
    private AmazonAutoScaling autoScaling;

    @RequestMapping(value = "/campbest", method = RequestMethod.GET)
    public String displayCampbest(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/campbest";
	}
    @RequestMapping(value = "/baackebg", method = RequestMethod.GET)
    public String displaybaackebg(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/baackebg";
	}
    @RequestMapping(value = "/gentilm5", method = RequestMethod.GET)
    public String displaygentilm5(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/gentilm5";
	}
    @RequestMapping(value = "/mcgregrs", method = RequestMethod.GET)
    public String displaymcgregrs(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/mcgregrs";
	}

    @RequestMapping(value = "/myersmw2", method = RequestMethod.GET)
    public String displaymyersmw2(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/myersmw2";
	}
    @RequestMapping(value = "/nguyent8", method = RequestMethod.GET)
    public String displaynguyent8(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/nguyent8";
	}
    @RequestMapping(value = "/nguyenq2", method = RequestMethod.GET)
    public String displaynguyenq2(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/nguyenq2";
	}
    @RequestMapping(value = "/postonj2", method = RequestMethod.GET)
    public String displaypostonj2(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/postonj2";
	}
    @RequestMapping(value = "/schmidcc", method = RequestMethod.GET)
    public String displayschmidcc(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/schmidcc";
	}
    @RequestMapping(value = "/siegerma", method = RequestMethod.GET)
    public String displaysiegerma(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/siegerma";
	}

    @RequestMapping(value = "/stoopsjb", method = RequestMethod.GET)
    public String displaystoopsjb(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/stoopsjb";
	}
    @RequestMapping(value = "/zhuj4", method = RequestMethod.GET)
    public String displayzhuj4(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/zhuj4";
	}







 

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayIndex(Model model) {
        LOGGER.info("Application name set to: " + applicationName);
        model.addAttribute("applicationName", applicationName);
        LOGGER.info("Deployment Group Name set to: " + deploymentGroupName);
        model.addAttribute("deploymentGroupName", deploymentGroupName);
        if (deploymentGroupName.contains("Production")) {
    		model.addAttribute("instanceIds", Collections.<String>emptyList());
    		return "/index";
    	}
        
        final GetDeploymentGroupResult getResult = codeDeploy.getDeploymentGroup(new GetDeploymentGroupRequest()
                .withApplicationName(applicationName)
                .withDeploymentGroupName(deploymentGroupName));

        final List<String> instanceIds = new ArrayList<>();
        for (final EC2TagFilter filter : getResult.getDeploymentGroupInfo().getEc2TagFilters()) {
            Filter ec2DescribeFilter = getFilter(filter);
            LOGGER.info("Calling EC2 Describe Instances with " + ec2DescribeFilter.getName() + " and values " + ec2DescribeFilter.getValues());
            final DescribeInstancesResult describeResult = ec2.describeInstances(new DescribeInstancesRequest()
                    .withFilters(Collections.singletonList(ec2DescribeFilter)));
            for (final Reservation reservation : describeResult.getReservations()) {
                for (final Instance instance : reservation.getInstances()) {
                    LOGGER.info("Found instance " + instance.getInstanceId());
                    instanceIds.add(instance.getInstanceId());
                }
            }
        }
        for (final AutoScalingGroup codeDeployGroup : getResult.getDeploymentGroupInfo().getAutoScalingGroups()) {
            LOGGER.info("Calling AutoScaling Describe Auto Scaling Groups with Auto Scaling Group Name " + codeDeployGroup.getName()); 
            final DescribeAutoScalingGroupsResult describeResult = autoScaling.describeAutoScalingGroups(new DescribeAutoScalingGroupsRequest()
                    .withAutoScalingGroupNames(codeDeployGroup.getName()));
            for (final com.amazonaws.services.autoscaling.model.AutoScalingGroup autoScalingGroup : describeResult.getAutoScalingGroups()) {
                for (final com.amazonaws.services.autoscaling.model.Instance instance : autoScalingGroup.getInstances()) {
                    LOGGER.info("Found instance " + instance.getInstanceId());
                    instanceIds.add(instance.getInstanceId());
                }
            }
        }
        final Map<String, String> instanceStates = new HashMap<>();
        if (!instanceIds.isEmpty()) {
            final DescribeInstanceStatusResult result = ec2.describeInstanceStatus(new DescribeInstanceStatusRequest()
    			    .withInstanceIds(instanceIds));
            for (final InstanceStatus status : result.getInstanceStatuses()) {
                LOGGER.info("Found instance " + status.getInstanceId() + " in state " + status.getInstanceStatus().getStatus());
            	instanceStates.put(status.getInstanceId(), status.getInstanceStatus().getStatus());
            }
        }
        model.addAttribute("instanceIds", instanceIds);
        model.addAttribute("instanceStates", instanceStates);
        return "/index";
    }

    private Filter getFilter(final EC2TagFilter tagFilter) {
        Filter filter;
        switch (tagFilter.getType()) {
        case "KEY_ONLY":
            filter = new Filter("tag-key", Collections.singletonList(tagFilter.getKey()));
            break;
        case "VALUE_ONLY":
            filter = new Filter("tag-value", Collections.singletonList(tagFilter.getValue()));
            break;
        case "KEY_AND_VALUE":
            filter = new Filter("tag:" + tagFilter.getKey(), Collections.singletonList(tagFilter.getValue()));
            break;
        default:
            throw new IllegalArgumentException("Unknown filter type " + tagFilter.getType());
        }
        return filter;
    }
}
